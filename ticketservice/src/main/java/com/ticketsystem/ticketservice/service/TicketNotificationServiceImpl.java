package com.ticketsystem.ticketservice.service;

import com.ticketsystem.servicecommunication.messaging.TicketNotification;
import com.ticketsystem.ticketservice.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class) //Kuyruğa mesaj atacak demek.
public class TicketNotificationServiceImpl implements TicketNotificationService{
    @Autowired
    private Source source;

    //Kuyruğa mesajı gönderecek metot.
    @Override
    public void sendToQueue(Ticket ticket) {
        TicketNotification ticketNotification=TicketNotification.builder()
                .ticketId(ticket.getId())
                .accountId(ticket.getAssignee())
                .ticketDescription(ticket.getDescription()).build();
        source.output().send(MessageBuilder.withPayload(ticketNotification).build());
        //erroChannel gibi detaylarıda verebiliriz withPayload'dan sonra
    }
}
