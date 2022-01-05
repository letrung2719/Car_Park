package com.example.carpark.service.impl;

import com.example.carpark.dto.TripDto;
import com.example.carpark.model.Trip;
import com.example.carpark.repository.TripRepository;
import com.example.carpark.service.ITripService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService implements ITripService {
    private TripRepository tripRepository;
    private ModelMapper modelMapper;

    public TripService(TripRepository tripRepository, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }

    // get all trips
    @Override
    public List<TripDto> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // add a new trip
    @Override
    public Trip addNewTrip(TripDto tripDto) {
        return tripRepository.save(this.mapToEntity(tripDto));
    }

    //delete trip by id
    @Override
    public String deleteTripById(Long id){
        if (this.existsById(id)){
            tripRepository.deleteById(id);
            return "Delete trip successfully!";
        }else{
            return "This id is not existed!";
        }
    }

    //check trip existed by id
    @Override
    public boolean existsById(Long id) {
        return tripRepository.existsById(id);
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
