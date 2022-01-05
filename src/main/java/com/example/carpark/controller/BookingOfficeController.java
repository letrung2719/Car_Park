package com.example.carpark.controller;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.model.BookingOffice;
import com.example.carpark.service.IBookingOfficeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking_office")
public class BookingOfficeController {
    private final IBookingOfficeService bookingOfficeService;

    public BookingOfficeController(IBookingOfficeService bookingOfficeService) {
        this.bookingOfficeService = bookingOfficeService;
    }

    @GetMapping("/view")
    public List<BookingOfficeDto> getAllBookingOffices() {
        return bookingOfficeService.getAllBookingOffices();
    }

    @PostMapping("/add")
    public BookingOffice addNewBookingOffice(@ModelAttribute BookingOfficeDto bookingOfficeDto) {
        return bookingOfficeService.addNewBookingOffice(bookingOfficeDto);
    }

    @GetMapping("/delete/{officeId}")
    public String deleteBookingOfficeById(@PathVariable("officeId") String officeId) {
        return bookingOfficeService.deleteBookingOfficeById(Long.parseLong(officeId));
    }
}
