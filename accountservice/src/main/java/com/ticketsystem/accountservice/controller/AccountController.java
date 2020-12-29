package com.ticketsystem.accountservice.controller;

import com.ticketsystem.accountservice.dto.AccountDto;
import com.ticketsystem.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "account")
/**
 * localhost:port/account
 * GET PUT DELETE POST
 */
public class AccountController {

    @Autowired
    //Context içerisinde bu class'dan bir nesne var onu bana ver buraya inject et demek.
    //AccountService veri tipindeki referansa Account nesnesinin referansı bu class içerisinde inject edilir
    private AccountService accountService;

    //Autowired yerine Constructor injection'da yapabilirdik o biraz daha performans açısından daha iyi olarak öneriliyor.
    /*
    private final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }*/

    //Geri dönüş nesnelerimi ResponseEntity classının tipiyle sarmalayıp döneriz. Böylece api'nin tüm response'ları aynı tip olmuş olur.
    //Aynı path üzerinde 4 farklı Http method isteği yayınlanabilir. Ama iki aynı tipte yayınlanamaz.
    /**
     * localhost:port/account/{id}
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") String id ){
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    /**
     * localhost:port/account
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto account){
        return ResponseEntity.ok(accountService.saveAccount(account));
    }

    /**
     * localhost:port/account
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") String id,
                                                    @RequestBody AccountDto account){
        return ResponseEntity.ok(accountService.updateAccount(id,account));
    }

    /**
     * localhost:port/account/{id}
     */
    //id yerine Account'da yollayabilirdik ama gereksiz yere fazla fieldlı(veri) olan Account nesnesini sunucuya göndermenin anlamı yok.
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    /**
     * localhost:port/account
     */
    //tüm kayıtları getirir
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Slice<AccountDto>> getAllAccounts(Pageable pageable){
        return ResponseEntity.ok(accountService.getAccounts(pageable));
    }

}
