package com.ticketsystem.accountservice.repository;

import com.ticketsystem.accountservice.entity.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CassandraRepository<Account,String> {
    //İhtiyacımız olan temel metotları JPA gibi içinde barındırıyor.
}
