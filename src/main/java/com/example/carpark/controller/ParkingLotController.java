package com.example.carpark.controller;

import com.example.carpark.dto.BookingOfficeDto;
import com.example.carpark.dto.ParkingLotDto;
import com.example.carpark.service.IBookingOfficeService;
import com.example.carpark.service.IParkingLotService;
import com.example.carpark.service.impl.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parking_lot")
public class ParkingLotController {
    @Autowired
    private IParkingLotService parkingLotService;

    @GetMapping("/view")
    public List<ParkingLotDto> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    @PostMapping("/add")
    public String addNewBookingOffice(@ModelAttribute ParkingLotDto parkingLotDto) {
        parkingLotService.addNewParkingLot(parkingLotDto);
        return "Add new parking lot successfully!";
    }

    @GetMapping("/delete/{parkId}")
    public String deleteParkingLotById(@PathVariable ("parkId") String parkId) {
        if (parkingLotService.existsById(Long.parseLong(parkId))){
            parkingLotService.deleteById(Long.parseLong(parkId));
            return "Delete parking lot successfully!";
        }else{
            return "This id is not existed!";
        }
    }
}
