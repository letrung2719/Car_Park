package com.example.carpark.controller;

import com.example.carpark.dto.CarDto;
import com.example.carpark.dto.TicketDto;
import com.example.carpark.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/view")
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/add")
    public String addNewTicket(@ModelAttribute TicketDto ticketDto) {
        ticketService.addNewTicket(ticketDto);
        return "Add new ticket successfully!";
    }

    @GetMapping("/delete/{ticketId}")
    public String deleteParkingLotById(@PathVariable ("ticketId") String ticketId) {
        if (ticketService.existsById(Long.parseLong(ticketId))){
            ticketService.deleteTicketById(Long.parseLong(ticketId));
            return "Delete ticket successfully!";
        }else{
            return "This id is not existed!";
        }
    }
}
