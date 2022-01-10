package com.example.carpark.service.impl;

import com.example.carpark.dto.ParkingLotDto;
import com.example.carpark.exception.ResourceNotFoundException;
import com.example.carpark.model.ParkingLot;
import com.example.carpark.repository.ParkingLotRepository;
import com.example.carpark.service.IParkingLotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParkingLotService implements IParkingLotService {
    private ParkingLotRepository parkingLotRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ModelMapper modelMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.modelMapper = modelMapper;
    }

    // get all parkingLots
    @Override
    public ResponseEntity<List<ParkingLotDto>> getAllParkingLots() {
        List<ParkingLotDto> list = parkingLotRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // get parkingLot by id
    @Override
    public ResponseEntity<ParkingLotDto> getParkingLotById(Long id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        ParkingLotDto parkingLotDto = this.mapToDto(parkingLot);
        return ResponseEntity.ok(parkingLotDto);
    }

    // add a new parkingLot
    @Override
    public ResponseEntity<ParkingLot> addNewParkingLot(ParkingLotDto parkingLotDto) {
        ParkingLot parkingLot = parkingLotRepository.save(this.mapToEntity(parkingLotDto));
        return ResponseEntity.ok(parkingLot);
    }

    //delete parkingLot by id
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteById(Long id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        parkingLotRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //convert Entity to DTO
    @Override
    public ParkingLotDto mapToDto(ParkingLot parkingLot) {
        ParkingLotDto parkingLotDto = modelMapper.map(parkingLot, ParkingLotDto.class);
        return parkingLotDto;
    }

    //convert DTO to Entity
    @Override
    public ParkingLot mapToEntity(ParkingLotDto parkingLotDto) {
        ParkingLot parkingLot = modelMapper.map(parkingLotDto, ParkingLot.class);
        return parkingLot;
    }
}
