package com.example.carpark.service;

import com.example.carpark.dto.ParkingLotDto;
import com.example.carpark.model.ParkingLot;

import java.util.List;

public interface IParkingLotService {
    // get all parkingLots
    List<ParkingLotDto> getAllParkingLots();

    // add a new parkingLot
    ParkingLot addNewParkingLot(ParkingLotDto parkingLotDto);

    //delete parkingLot by id
    String deleteById(Long id);

    //check parkingLot existed
    boolean existsById(Long aLong);

    //convert Entity to DTO
    ParkingLotDto mapToDto(ParkingLot parkingLot);

    //convert DTO to Entity
    ParkingLot mapToEntity(ParkingLotDto parkingLotDto);
}
