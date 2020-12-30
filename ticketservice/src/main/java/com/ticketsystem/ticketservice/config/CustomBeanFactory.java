package com.ticketsystem.ticketservice.config;

import org.modelmapper.ModelMapper;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//Bu ikisini ticket-service.yml üzerinde de enable edebilirdik.
@EnableElasticsearchRepositories
@EnableJpaRepositories("com.ticketsystem") //TicketService'i TicketRepository'i path'ini bilmediği için implemente edemedi path ekledik.
@ComponentScan("com.ticketsystem")
public class CustomBeanFactory {
    @Bean
    public ModelMapper getModelMapper(){
        //Eğer ModelMapper'da detay konfigürasyon yapacak olursa her yerde o şekilde kullanıcaz.
        return new ModelMapper();
    }
}
