package com.example.carpark.service.impl;

import com.example.carpark.dto.TicketDto;
import com.example.carpark.exception.ResourceNotFoundException;
import com.example.carpark.model.Ticket;
import com.example.carpark.repository.TicketRepository;
import com.example.carpark.service.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    private TicketRepository ticketRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    //get all tickets
    @Override
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> list = ticketRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //get ticket by id
    @Override
    public ResponseEntity<TicketDto> getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("This id " + id + " does not exists!"));
        TicketDto ticketDto = this.mapToDto(ticket);
        return ResponseEntity.ok(ticketDto);
    }

    //add new ticket
    @Override
    public ResponseEntity<Ticket> addNewTicket(TicketDto tickerDto) {
        Ticket ticket = ticketRepository.save(this.mapToEntity(tickerDto));
        return ResponseEntity.ok(ticket);
    }

    //delete ticket by id
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        ticketRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //convert Entity to DTO
    @Override
    public TicketDto mapToDto(Ticket ticket) {
        TicketDto tickerDto = modelMapper.map(ticket, TicketDto.class);
        return tickerDto;
    }

    //convert DTO to Entity
    @Override
    public Ticket mapToEntity(TicketDto tickerDto) {
        Ticket ticket = modelMapper.map(tickerDto, Ticket.class);
        return ticket;
    }
}
