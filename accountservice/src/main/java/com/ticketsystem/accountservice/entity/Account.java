package com.ticketsystem.accountservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter //Hepsinde getter olsun ama bazılarında setter olmasın.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
//Birden fazla field belirtebiliriz. İki nesnenin id'si eşitse onlar birbirine eşittir. Bunu yazmasak, Account@1234 gibi referansları eşit olunca eşit olurlardı.
@ToString
//https://koraypeker.com/2018/06/10/project-lombok/
//https://medium.com/@metinalniacik/equals-ve-hashcode-metotlar%C4%B1n%C4%B1n-%C3%B6nemi-nedir-800c99af1519
@Table(value = "accounts")
public class Account implements Serializable {

    //Fields
    @PrimaryKey
    private String id = UUID.randomUUID().toString(); //Her instance yaratıldığında yaratılacak.
    @Setter
    @Column("uname") //custom bir reserved key olmamasına dikkat etmeliyiz her db'de değişir.
    private String userName;
    @Setter
    @Column("name")
    private String name;
    @Setter
    @Column("surname")
    private String surname;
    @Setter
    @Column("birth_date")
    private Date birthDate;
    @Setter
    @Column("email")
    private String email;
    @Setter
    @Column("pwd")
    private String passwd;

    //Bunlara kimse bir şey set etmesin.
    @Column("created_at")
    private Date createdAt;
    @Column("is_active")
    private Boolean active;
}
