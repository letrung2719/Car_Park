package com.example.carpark.controller;

import com.example.carpark.dto.TripDto;
import com.example.carpark.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trip")
public class TripController {
    @Autowired
    private ITripService tripService;

    @GetMapping("/view")
    public List<TripDto> getAllTrips(){
        return tripService.getAllTrips();
    }

    @PostMapping("/add")
    public String addNewTrip(@ModelAttribute TripDto tripDto){
        tripService.addNewTrip(tripDto);
        return "Add a new trip successfully!";
    }

    @GetMapping("/delete/{trip_id}")
    public String deleteTripById(@PathVariable("trip_id") String trip_id){
        if (tripService.existsById(Long.parseLong(trip_id))){
            tripService.deleteTripById(Long.parseLong(trip_id));
            return "Delete trip successfully!";
        }else{
            return "This id is not existed!";
        }
    }
}
