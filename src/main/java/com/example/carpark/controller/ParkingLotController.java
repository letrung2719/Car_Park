package com.example.carpark.controller;

import com.example.carpark.dto.ParkingLotDto;
import com.example.carpark.model.ParkingLot;
import com.example.carpark.service.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car-park")
public class ParkingLotController {
    private final IParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(IParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/parking-lot/list")
    public ResponseEntity<List<ParkingLotDto>> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    @GetMapping("/parking-lot/get/{parkId}")
    public ResponseEntity<ParkingLotDto> getParkingById(@PathVariable("parkId") String parkId) {
        return parkingLotService.getParkingLotById(Long.parseLong(parkId));
    }

    @PostMapping("/parking-lot/add")
    public ResponseEntity<ParkingLot> addNewBookingOffice(@ModelAttribute ParkingLotDto parkingLotDto) {
        return parkingLotService.addNewParkingLot(parkingLotDto);
    }

    @DeleteMapping("/parking-lot/delete/{parkId}")
    public ResponseEntity<Map<String, Boolean>> deleteParkingLotById(@PathVariable("parkId") String parkId) {
        return parkingLotService.deleteById(Long.parseLong(parkId));
    }
}
