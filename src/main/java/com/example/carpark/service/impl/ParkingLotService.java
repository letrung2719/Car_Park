package com.example.carpark.service.impl;

import com.example.carpark.dto.ParkingLotDto;
import com.example.carpark.model.ParkingLot;
import com.example.carpark.repository.ParkingLotRepository;
import com.example.carpark.service.IParkingLotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService implements IParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    private ModelMapper modelMapper;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ModelMapper modelMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.modelMapper = modelMapper;
    }

    // get all parkingLots
    @Override
    public List<ParkingLotDto> getAllParkingLots() {
        return parkingLotRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // add a new parkingLot
    @Override
    public void addNewParkingLot(ParkingLotDto parkingLotDto) {
        parkingLotRepository.save(this.mapToEntity(parkingLotDto));
    }

    //delete parkingLot by id
    @Override
    public void deleteById(Long id) {
        parkingLotRepository.deleteById(id);
    }

    //check parkingLot existed
    @Override
    public boolean existsById(Long aLong) {
        return parkingLotRepository.existsById(aLong);
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
