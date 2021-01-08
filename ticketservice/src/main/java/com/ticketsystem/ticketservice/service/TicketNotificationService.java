package com.ticketsystem.ticketservice.service;

import com.ticketsystem.ticketservice.entity.Ticket;

public interface TicketNotificationService {
    void sendToQueue(Ticket ticket);
}
