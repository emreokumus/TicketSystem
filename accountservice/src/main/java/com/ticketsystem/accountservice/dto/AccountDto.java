package com.ticketsystem.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Getter,Setter,ToString,EqualsAndHashCode,RequiredArgsConstructor ve Value eklenir
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id;

    private String userName;

    private String name;

    private String surname;

    private Date birthDate;

    private String email;
}
