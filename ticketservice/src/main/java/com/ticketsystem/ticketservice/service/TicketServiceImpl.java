package com.ticketsystem.ticketservice.service;

import com.ticketsystem.servicecommunication.client.AccountServiceClient;
import com.ticketsystem.servicecommunication.client.contract.AccountDto;
import com.ticketsystem.servicecommunication.messaging.TicketNotification;
import com.ticketsystem.ticketservice.dto.TicketDto;
import com.ticketsystem.ticketservice.entity.PriorityType;
import com.ticketsystem.ticketservice.entity.Ticket;
import com.ticketsystem.ticketservice.entity.TicketStatus;
import com.ticketsystem.ticketservice.entity.elasticsearch.TicketModel;
import com.ticketsystem.ticketservice.repository.TicketRepository;
import com.ticketsystem.ticketservice.repository.elasticsearch.TicketElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@RequiredArgsConstructor //aşağıdaki Autowired alanları yazmak yerinde dırek bunu yazabılırdık.
//Gerekli olan dependency'ler ile constructor injection yaratılmasını sağlıyor.
public class TicketServiceImpl implements TicketService{
    /*
    //RequiredArgsConstructor kullansaydık @Autowired olanlar yerine bunları kullanacaktık.
    private final TicketRepository ticketRepository;
    private final TicketElasticRepository elasticRepository;
    private final ModelMapper modelMapper;*/
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketElasticRepository elasticRepository;

    //Feign client'ı inject ederiz ve o interface'den otomatik instance'i yaratılacak.
    //private final AccountServiceClient accountServiceClient;
    @Autowired
    private AccountServiceClient accountServiceClient;

    //Kuyruğa TicketNotification tipinde mesaj yollayan service.
    @Autowired
    private TicketNotificationService ticketNotificationService;

    @Transactional
    @Override
    public TicketDto save(TicketDto ticketDto) {

        //TicketDto to TicketEntity dönüşümü yapılır.
        Ticket ticket=new Ticket();
        //TicketDto'daki String olan enum'a ait kısımları Entity'de Enum type ile maplemesi gerektiği için ek konfigürasyon gerekecek o yüzden farklı şekilde yapıcaz.
        //Ticket ticket=modelMapper.map(ticketDto,Ticket.class);
        if(ticketDto.getDescription()==null){
            throw new RuntimeException("Description boş olamaz.");
        }
        //Account API'dan doğrulama.
        try {
            ResponseEntity<AccountDto> accountDtoResponseEntity=accountServiceClient.getAccount(ticketDto.getAssignee());
            ticket.setAssignee(accountDtoResponseEntity.getBody().getId()); //assignee id'i mysql'e kaydeder.

            ticket.setDescription(ticketDto.getDescription());
            ticket.setNotes(ticketDto.getNotes());
            ticket.setTicketDate(ticketDto.getTicketDate());
            ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
            ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
            //MySQL'e kaydedecek.
            ticket = ticketRepository.save(ticket);

            //TicketEntity to TicketModel dönüşümü yapılır.
            //Burada ModelMapper kullanabiliriz. Alan tipleri aynı veya direkt entity'den model'e kontrollü şelilde builder ile constructor ile set edebiliriz.
            TicketModel model = TicketModel.builder()
                    .description(ticket.getDescription())
                    .notes(ticket.getNotes())
                    .id(ticket.getId())
                    .assignee(accountDtoResponseEntity.getBody().getNameSurname())//assignee ad soyadını elasticsearch'e kaydeder
                    .priorityType(ticket.getPriorityType().getLabel())
                    .ticketStatus(ticket.getTicketStatus().getLabel())
                    .ticketDate(ticket.getTicketDate()).build();
            //ElasticSearch'e kaydedecek.
            elasticRepository.save(model);
            //Response olarak oluşan nesneyi döndür.
            ticketDto.setId(ticket.getId());
            //Kuyruğa notigication mesajını gönder.
            ticketNotificationService.sendToQueue(ticket);

            return ticketDto;
        }catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("Assiggne bulunamadığı için kayıt yapılamadı.");
        }
    }

    //Save'in aynısı
    @Override
    public TicketDto update(String id,TicketDto ticketDto) {
        return null;
    }

    //MySQL'den getireceğiz.
    @Override
    public TicketDto getById(String ticketId) {
        return null;
    }

    //Bu sadece TicketElasticRepository'e baglayacağız.
    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }


}
