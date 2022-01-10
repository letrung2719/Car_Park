package com.example.carpark.service;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.model.BookingOffice;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IBookingOfficeService {
    //get all booking offices
    ResponseEntity<List<BookingOfficeDto>> getAllBookingOffices();

    //get booking office by id
    ResponseEntity<BookingOfficeDto> getBookingOfficeById(Long id);

    //add new booking office
    ResponseEntity<BookingOffice> addNewBookingOffice(BookingOfficeDto bookingOfficeDto);

    //delete booking office by id
    ResponseEntity<Map<String, Boolean>> deleteBookingOfficeById(Long id);

    //convert Entity to DTO
    BookingOfficeDto mapToDto(BookingOffice bookingOffice);

    //convert DTO to Entity
    BookingOffice mapToEntity(BookingOfficeDto bookingOfficeDto);
}
