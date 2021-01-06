package com.ticketsystem.servicecommunication.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
//Her service içerisinde tek tek enable etmek yerine ortakta enable ettik.
@EnableFeignClients(basePackages = {"com.ticketsystem.servicecommunication.client"})
//Bean'i bulması için ve implemente edebilmemiz için package'ini belirtmemiz lazım.
public class FeignConfig {
}