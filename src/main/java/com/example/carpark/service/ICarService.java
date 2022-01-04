package com.example.carpark.service;

import com.example.carpark.dto.CarDto;
import com.example.carpark.model.Car;

import java.util.List;

public interface ICarService {
    //get all cars
    List<CarDto> getAllCars();

    //add new car
    void addNewCar(CarDto carDto);

    //delete car by license plate
    void deleteCarByLicensePlate(String s);

    //check car existed
    boolean existsById(String s);

    //convert Entity to DTO
    CarDto mapToDto(Car car);

    ///convert DTO to Entity
    Car mapToEntity(CarDto carDto);
}
