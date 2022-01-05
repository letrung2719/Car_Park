package com.example.carpark.service;

import com.example.carpark.dto.TicketDto;
import com.example.carpark.model.Ticket;

import java.util.List;

public interface ITicketService {
    //get all tickets
    List<TicketDto> getAllTickets();

    //add new ticket
    Ticket addNewTicket(TicketDto tickerDto);

    //delete ticket by id
    String deleteTicketById(Long id);

    //check ticket existed
    boolean existsById(Long id);

    //convert Entity to DTO
    TicketDto mapToDto(Ticket ticket);

    //convert DTO to Entity
    Ticket mapToEntity(TicketDto tickerDto);
}
