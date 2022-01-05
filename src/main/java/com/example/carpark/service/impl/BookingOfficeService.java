package com.example.carpark.service.impl;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.model.BookingOffice;
import com.example.carpark.repository.BookingOfficeRepository;
import com.example.carpark.service.IBookingOfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingOfficeService implements IBookingOfficeService {
    private BookingOfficeRepository bookingOfficeRepository;
    private ModelMapper modelMapper;

    public BookingOfficeService(BookingOfficeRepository bookingOfficeRepository, ModelMapper modelMapper) {
        this.bookingOfficeRepository = bookingOfficeRepository;
        this.modelMapper = modelMapper;
    }

    //get all booking offices
    @Override
    public List<BookingOfficeDto> getAllBookingOffices() {
        System.out.println(modelMapper);
        return bookingOfficeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //add new booking office
    @Override
    public BookingOffice addNewBookingOffice(BookingOfficeDto bookingOfficeDto) {
        return bookingOfficeRepository.save(this.mapToEntity(bookingOfficeDto));
    }

    //delete booking office by id
    @Override
    public String deleteBookingOfficeById(Long id) {
        if (this.existsById(id)){
            bookingOfficeRepository.deleteById(id);
            return "Delete booking office successfully!";
        }else{
            return "This id is not existed!";
        }
    }

    //check booking office existed
    @Override
    public boolean existsById(Long id) {
        return bookingOfficeRepository.existsById(id);
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
