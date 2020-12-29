package com.ticketsystem.ticketservice.entity;

import lombok.Getter;

@Getter
public enum TicketStatus {
    OPEN("Açık"),
    IN_PROGRESS("Üzerinde çalışıyor"),
    RESOLVED("Çözüldü"),
    CLOSED("Kapandı");

    private String label;

    TicketStatus(String label){
        this.label=label;
    }
}
