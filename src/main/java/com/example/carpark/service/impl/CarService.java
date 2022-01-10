package com.example.carpark.service.impl;

import com.example.carpark.dto.CarDto;
import com.example.carpark.exception.ResourceNotFoundException;
import com.example.carpark.model.Car;
import com.example.carpark.repository.CarRepository;
import com.example.carpark.service.ICarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {
    private CarRepository carRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    //get all cars
    @Override
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> list = carRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //get car by license plate
    @Override
    public ResponseEntity<CarDto> getCarByLicensePlate(String licensePlate) {
        Car car = carRepository.findById(licensePlate)
                .orElseThrow(()-> new ResourceNotFoundException("This license plate " + licensePlate + " does not exists!"));
        CarDto carDto = this.mapToDto(car);
        return ResponseEntity.ok(carDto);
    }

    //add new car
    @Override
    public ResponseEntity<Car> addNewCar(CarDto carDto) {
        Car car = carRepository.save(this.mapToEntity(carDto));
        return ResponseEntity.ok(car);
    }

    //delete car by license plate
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteCarByLicensePlate(String licensePlate) {
        Car car = carRepository.findById(licensePlate)
                .orElseThrow(()-> new ResourceNotFoundException("This license plate " + licensePlate + " does not exists!"));
        carRepository.deleteById(licensePlate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //convert Entity to DTO
    @Override
    public CarDto mapToDto(Car car) {
        CarDto carDto = modelMapper.map(car, CarDto.class);
        return carDto;
    }

    //convert DTO to Entity
    @Override
    public Car mapToEntity(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        return car;
    }
}
