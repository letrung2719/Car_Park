package com.example.carpark.service.impl;

import com.example.carpark.dto.TicketDto;
import com.example.carpark.model.Ticket;
import com.example.carpark.repository.TicketRepository;
import com.example.carpark.service.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    private TicketRepository ticketRepository;
    private ModelMapper modelMapper;

    public TicketService(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    //get all tickets
    @Override
    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //add new ticket
    @Override
    public Ticket addNewTicket(TicketDto tickerDto) {
        return ticketRepository.save(this.mapToEntity(tickerDto));
    }

    //delete ticket by id
    @Override
    public String deleteTicketById(Long id) {
        if (this.existsById(id)){
            ticketRepository.deleteById(id);
            return "Delete ticket successfully!";
        }else{
            return "This id is not existed!";
        }
    }

    //check ticket existed
    @Override
    public boolean existsById(Long id) {
        return ticketRepository.existsById(id);
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
