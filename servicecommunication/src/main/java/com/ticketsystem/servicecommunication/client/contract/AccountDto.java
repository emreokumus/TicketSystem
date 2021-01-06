package com.ticketsystem.servicecommunication.client.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//Bu hem feign client'in hemde Account Service controllerinin kullanacağı ortak DTO classıdır.
//Bunu Account Service'in kullanabilmesi için dependency'sini pom.xml içerisine eklemeliyiz.

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

    public String getNameSurname() {
        return this.name + " " + this.surname;
    }
}
