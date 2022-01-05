package com.example.carpark.service;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.model.BookingOffice;

import java.util.List;

public interface IBookingOfficeService {
    //get all booking offices
    List<BookingOfficeDto> getAllBookingOffices();

    //add new booking office
    BookingOffice addNewBookingOffice(BookingOfficeDto bookingOfficeDto);

    //delete booking office by id
    String deleteBookingOfficeById(Long id);

    //check booking office existed
    boolean existsById(Long id);

    //convert Entity to DTO
    BookingOfficeDto mapToDto(BookingOffice bookingOffice);

    //convert DTO to Entity
    BookingOffice mapToEntity(BookingOfficeDto bookingOfficeDto);
}
