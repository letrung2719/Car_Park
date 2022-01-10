package com.example.carpark.controller;

import com.example.carpark.dto.TicketDto;
import com.example.carpark.model.Ticket;
import com.example.carpark.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car-park")
public class TicketController {
    private final ITicketService ticketService;

    @Autowired
    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket/list")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/get/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable("ticketId") String ticketId) {
        return ticketService.getTicketById(Long.parseLong(ticketId));
    }

    @PostMapping("/ticket/add")
    public ResponseEntity<Ticket> addNewTicket(@ModelAttribute TicketDto ticketDto) {
        return ticketService.addNewTicket(ticketDto);
    }

    @DeleteMapping("/ticket/delete/{ticketId}")
    public ResponseEntity<Map<String, Boolean>> deleteParkingLotById(@PathVariable("ticketId") String ticketId) {
        return ticketService.deleteTicketById(Long.parseLong(ticketId));
    }
}
