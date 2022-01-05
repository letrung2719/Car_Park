package com.example.carpark.controller;

import com.example.carpark.dto.TicketDto;
import com.example.carpark.model.Ticket;
import com.example.carpark.service.ITicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    private final ITicketService ticketService;

    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/view")
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/add")
    public Ticket addNewTicket(@ModelAttribute TicketDto ticketDto) {
        return ticketService.addNewTicket(ticketDto);
    }

    @GetMapping("/delete/{ticketId}")
    public String deleteParkingLotById(@PathVariable("ticketId") String ticketId) {
        return ticketService.deleteTicketById(Long.parseLong(ticketId));
    }
}
