package com.ticketsystem.notificationservice.service;

import com.ticketsystem.servicecommunication.messaging.TicketNotification;

public interface NotificationService {
    void onNotification(TicketNotification ticketNotification);
}
