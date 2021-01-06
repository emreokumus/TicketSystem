package com.ticketsystem.ticketservice.controller;

import com.ticketsystem.ticketservice.dto.TicketDto;
import com.ticketsystem.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ticket")
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto){
        try {
            TicketDto ticketDtoResponse=ticketService.save(ticketDto);
            return ResponseEntity.ok(ticketDtoResponse);
        }catch (IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable("id") String id,
                                                  @RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.update(id,ticketDto));
    }

    @GetMapping
    public ResponseEntity<Page<TicketDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(ticketService.getPagination(pageable));
    }
}
