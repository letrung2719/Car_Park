package com.example.carpark.service.impl;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.exception.ResourceNotFoundException;
import com.example.carpark.model.BookingOffice;
import com.example.carpark.repository.BookingOfficeRepository;
import com.example.carpark.service.IBookingOfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingOfficeService implements IBookingOfficeService {
    private BookingOfficeRepository bookingOfficeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BookingOfficeService(BookingOfficeRepository bookingOfficeRepository, ModelMapper modelMapper) {
        this.bookingOfficeRepository = bookingOfficeRepository;
        this.modelMapper = modelMapper;
    }

    //get all booking offices
    @Override
    public ResponseEntity<List<BookingOfficeDto>> getAllBookingOffices() {
        List<BookingOfficeDto> list = bookingOfficeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //get booking office by id
    @Override
    public ResponseEntity<BookingOfficeDto> getBookingOfficeById(Long id) {
        BookingOffice bookingOffice = bookingOfficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        BookingOfficeDto bookingOfficeDto = this.mapToDto(bookingOffice);
        return ResponseEntity.ok(bookingOfficeDto);
    }

    //add new booking office
    @Override
    public ResponseEntity<BookingOffice> addNewBookingOffice(BookingOfficeDto bookingOfficeDto) {
        BookingOffice bookingOffice = bookingOfficeRepository.save(this.mapToEntity(bookingOfficeDto));
        return ResponseEntity.ok(bookingOffice);
    }

    //delete booking office by id
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteBookingOfficeById(Long id) throws ResourceNotFoundException{
        BookingOffice bookingOffice = bookingOfficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        bookingOfficeRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //convert Entity to DTO
    @Override
    public BookingOfficeDto mapToDto(BookingOffice bookingOffice) {
        BookingOfficeDto bookingOfficeDto = modelMapper.map(bookingOffice, BookingOfficeDto.class);
        return bookingOfficeDto;
    }

    //convert DTO to Entity
    @Override
    public BookingOffice mapToEntity(BookingOfficeDto bookingOfficeDto) {
        BookingOffice bookingOffice = modelMapper.map(bookingOfficeDto, BookingOffice.class);
        return bookingOffice;
    }
}
