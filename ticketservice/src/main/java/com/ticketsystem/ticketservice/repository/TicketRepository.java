package com.ticketsystem.ticketservice.repository;

import com.ticketsystem.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//MySQL için
@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
}
