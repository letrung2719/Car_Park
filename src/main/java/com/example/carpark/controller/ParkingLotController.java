package com.example.carpark.controller;

import com.example.carpark.dto.ParkingLotDto;
import com.example.carpark.model.ParkingLot;
import com.example.carpark.service.IParkingLotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parking_lot")
public class ParkingLotController {
    private final IParkingLotService parkingLotService;

    public ParkingLotController(IParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/view")
    public List<ParkingLotDto> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    @PostMapping("/add")
    public ParkingLot addNewBookingOffice(@ModelAttribute ParkingLotDto parkingLotDto) {
        return parkingLotService.addNewParkingLot(parkingLotDto);
    }

    @GetMapping("/delete/{parkId}")
    public String deleteParkingLotById(@PathVariable("parkId") String parkId) {
        return parkingLotService.deleteById(Long.parseLong(parkId));
    }
}
