package com.ticketsystem.ticketservice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
@EqualsAndHashCode(of={"id"}) //iki nesnenin birbirine equals olmasını sağlayan id field'ıdır.
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseEntityModel{ //extends edince abstractaki kolonları buna kazandırmış olucaz.

    @Id
    @Getter //Id auto_generated bir field olacak setter'ı olmaz bu yuzden
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;
    @Getter
    @Setter
    @Column(name="description",length = 600)
    private String description;
    @Getter
    @Setter
    @Column(name="notes",length = 4000)
    private String notes;
    @Getter
    @Setter
    @Column(name="assignee",length = 50) //Bu diğer taraftan gelecek uuid'dir.
    //Bu assignee bir Account'a bağlanacak join işlemi yapmayacağız microservice olduğu için o Account Entity'sini bu microservice bilmez.
    //Ama bunu kendim validate edicem.Ticket Service gidip Account Service'e Eureka Server üzerinden soracak assigne doğru mu diye validasyonunu yapıcaz.
    //Burada foreign key filan olmayacak. Normalde foreign key oluyor olsaydı o Entity'de burada mevcut olsaydı tipini Account verip join anotasyonlarını kullanıcaktım.
    private String assignee;
    @Getter
    @Setter
    @Column(name="ticket_date")
    private Date ticketDate;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL) //enumeration'dan gelen bir field bunun default'ı ORDINAL'dir değerleri 0(urgent),1(low),2(high) diye rakamla tutar.
    @Column(name="priority_type")
    //Bunu bir enumeration olarak vericem.
    private PriorityType priorityType;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL) //default ORDINAL
    //Bunu bir enumeration olarak vericem.
    private TicketStatus ticketStatus;
}
