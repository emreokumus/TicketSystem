package com.ticketsystem.accountservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanFactory {
    //Proje içerisinde 1 tane singleton ModelMapper instance'ini oluşturmayı sağlarız.
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
