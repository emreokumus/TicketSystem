package com.ticketsystem.ticketservice.entity.elasticsearch;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

//Bu benim ElasticSearch objem.

@Data
@Builder
@EqualsAndHashCode(of = { "id" } ) //iki tane ticket model nesnesinin birbirine eşit olup olmadığına id göre bakıcam
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "ticket") //index adı, bir indeksi birden fazla model kullanıyorsa hepsine ortak type vermeliyiz.
//ElasticSearch'de 1'den fazla instance'imiz olduğu zaman birden fazla instance üzerinde veriyi saklarken kaç tane replica shard olacak gibi özelliklerde verebiliriz.
public class TicketModel implements Serializable {
    //Ticket'in ElasticSearch üzerinde aramak istediğim özelliklerini ekleyeceğim.

    @Id //Id spring data'nın id'si olmalı
    private String id;

    private String description;

    private String notes;

    private String assignee;

    private Date ticketDate;

    private String priorityType;

    private String ticketStatus;
}
