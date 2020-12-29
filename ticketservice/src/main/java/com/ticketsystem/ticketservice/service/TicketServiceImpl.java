package com.ticketsystem.ticketservice.service;

import com.ticketsystem.ticketservice.dto.TicketDto;
import com.ticketsystem.ticketservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    @Override
    public TicketDto save(TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto update(String id,TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getById(String ticketId) {
        return null;
    }

    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }
}
