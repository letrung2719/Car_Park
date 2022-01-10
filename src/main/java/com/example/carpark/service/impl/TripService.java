package com.example.carpark.service.impl;

import com.example.carpark.dto.TripDto;
import com.example.carpark.exception.ResourceNotFoundException;
import com.example.carpark.model.Trip;
import com.example.carpark.repository.TripRepository;
import com.example.carpark.service.ITripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TripService implements ITripService {
    private TripRepository tripRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TripService(TripRepository tripRepository, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }

    // get all trips
    @Override
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> list = tripRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // get trip by id
    @Override
    public ResponseEntity<TripDto> getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        TripDto tripDto = this.mapToDto(trip);
        return ResponseEntity.ok(tripDto);
    }

    // add a new trip
    @Override
    public ResponseEntity<Trip> addNewTrip(TripDto tripDto) {
        Trip trip = tripRepository.save(this.mapToEntity(tripDto));
        return ResponseEntity.ok(trip);
    }

    //delete trip by id
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteTripById(Long id){
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        tripRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //convert Entity to DTO
    @Override
    public TripDto mapToDto(Trip trip) {
        TripDto tripDto = modelMapper.map(trip, TripDto.class);
        return tripDto;
    }

    //convert DTO to Entity
    @Override
    public Trip mapToEntity(TripDto tripDto) {
        Trip trip = modelMapper.map(tripDto, Trip.class);
        return trip;
    }
}
