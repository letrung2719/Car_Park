package com.example.carpark.service;

import com.example.carpark.dto.TripDto;
import com.example.carpark.model.Trip;

import java.util.List;

public interface ITripService {
    // get all trips
    List<TripDto> getAllTrips();

    // add a new trip
    void addNewTrip(TripDto tripDto);

    //delete trip by id
    void deleteTripById(Long id);

    //check trip existed by id
    boolean existsById(Long id);

    //convert Entity to DTO
    TripDto mapToDto(Trip trip);

    //convert DTO to Entity
    Trip mapToEntity(TripDto tripDto);
}
