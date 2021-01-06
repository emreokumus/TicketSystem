package com.ticketsystem.servicecommunication.client;

import com.ticketsystem.servicecommunication.client.contract.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account-service") //Account Service ile iletişim kuracak demek.
public interface AccountServiceClient {

    //Account Service içerisindeki getAccount(id) handler metodunu map etmem lazım.
    //Bu metodu çağıran AccountDto alır. Bu AccountDto, Account Service'de ki DTO'nun aynısı.
    @RequestMapping(value = "/account/{id}",method = RequestMethod.GET)
    ResponseEntity<AccountDto> getAccount(@PathVariable("id") String id );
}
