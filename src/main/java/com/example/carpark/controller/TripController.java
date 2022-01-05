package com.example.carpark.controller;

import com.example.carpark.dto.TripDto;
import com.example.carpark.model.Trip;
import com.example.carpark.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trip")
public class TripController {
    private final ITripService tripService;

    public TripController(ITripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/view")
    public List<TripDto> getAllTrips() {
        return tripService.getAllTrips();
    }

    @PostMapping("/add")
    public Trip addNewTrip(@ModelAttribute TripDto tripDto) {
        return tripService.addNewTrip(tripDto);
    }

    @GetMapping("/delete/{trip_id}")
    public String deleteTripById(@PathVariable("trip_id") String trip_id) {
        return tripService.deleteTripById(Long.parseLong(trip_id));
    }
}
