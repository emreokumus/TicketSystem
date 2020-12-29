package com.ticketsystem.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private String id;

    private String description;

    private String notes;

    private String assignee;

    private Date ticketDate;

    //Enumerationları dışarı açarken dışarıdaki kişinin benım enumerationımın detaylarını bilmesine gerek yok o yüzden string.
    private String priorityType;

    private String ticketStatus;
}
