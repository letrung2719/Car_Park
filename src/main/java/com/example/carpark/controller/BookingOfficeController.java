package com.example.carpark.controller;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.service.IBookingOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking_office")
public class BookingOfficeController {
    @Autowired
    private IBookingOfficeService bookingOfficeService;

    @GetMapping("/view")
    public List<BookingOfficeDto> getAllBookingOffices() {
        return bookingOfficeService.getAllBookingOffices();
    }

    @PostMapping("/add")
    public String addNewBookingOffice(@ModelAttribute BookingOfficeDto bookingOfficeDto) {
        bookingOfficeService.addNewBookingOffice(bookingOfficeDto);
        return "Add new booking office successfully!";
    }

    @GetMapping("/delete/{officeId}")
    public String deleteBookingOfficeById(@PathVariable ("officeId") String officeId) {
        if (bookingOfficeService.existsById(Long.parseLong(officeId))){
            bookingOfficeService.deleteBookingOfficeById(Long.parseLong(officeId));
            return "Delete booking office successfully!";
        }else{
            return "This id is not existed!";
        }
    }
}
