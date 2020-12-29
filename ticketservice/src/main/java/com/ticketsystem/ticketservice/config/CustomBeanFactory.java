package com.ticketsystem.ticketservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanFactory {
    @Bean
    public ModelMapper getModelMapper(){
        //Eğer ModelMapper'da detay konfigürasyon yapacak olursa her yerde o şekilde kullanıcaz.
        return new ModelMapper();
    }
}
