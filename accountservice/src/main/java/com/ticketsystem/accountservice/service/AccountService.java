package com.ticketsystem.accountservice.service;

import com.ticketsystem.servicecommunication.client.contract.AccountDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface AccountService {
    AccountDto getAccount(String id);
    Slice<AccountDto> getAccounts(Pageable pageable);
    AccountDto saveAccount(AccountDto account);
    AccountDto updateAccount(String id,AccountDto account);
    void deleteAccount(String id);
}
