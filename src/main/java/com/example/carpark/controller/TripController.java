package com.example.carpark.controller;

import com.example.carpark.dto.TripDto;
import com.example.carpark.model.Trip;
import com.example.carpark.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car-park")
public class TripController {
    private final ITripService tripService;

    @Autowired
    public TripController(ITripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trip/list")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/trip/get/{trip_id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable("trip_id") String trip_id) {
        return tripService.getTripById(Long.parseLong(trip_id));
    }

    @PostMapping("/trip/add")
    public ResponseEntity<Trip> addNewTrip(@ModelAttribute TripDto tripDto) {
        return tripService.addNewTrip(tripDto);
    }

    @DeleteMapping("/trip/delete/{trip_id}")
    public ResponseEntity<Map<String, Boolean>> deleteTripById(@PathVariable("trip_id") String trip_id) {
        return tripService.deleteTripById(Long.parseLong(trip_id));
    }
}
