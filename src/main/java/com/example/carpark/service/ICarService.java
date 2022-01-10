package com.example.carpark.service;

import com.example.carpark.dto.CarDto;
import com.example.carpark.model.Car;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICarService {
    //get all cars
    ResponseEntity<List<CarDto>> getAllCars();

    //get car by license plate
    ResponseEntity<CarDto> getCarByLicensePlate(String licensePlate);

    //add new car
    ResponseEntity<Car> addNewCar(CarDto carDto);

    //delete car by license plate
    ResponseEntity<Map<String, Boolean>> deleteCarByLicensePlate(String s);

    //convert Entity to DTO
    CarDto mapToDto(Car car);

    ///convert DTO to Entity
    Car mapToEntity(CarDto carDto);
}
