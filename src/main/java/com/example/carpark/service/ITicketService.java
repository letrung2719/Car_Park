package com.example.carpark.service;

import com.example.carpark.dto.TicketDto;
import com.example.carpark.model.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITicketService {
    //get all tickets
    ResponseEntity<List<TicketDto>> getAllTickets();

    //get ticket by id
    ResponseEntity<TicketDto> getTicketById(Long id);

    //add new ticket
    ResponseEntity<Ticket> addNewTicket(TicketDto tickerDto);

    //delete ticket by id
    ResponseEntity<Map<String, Boolean>> deleteTicketById(Long id);

    //convert Entity to DTO
    TicketDto mapToDto(Ticket ticket);

    //convert DTO to Entity
    Ticket mapToEntity(TicketDto tickerDto);
}
