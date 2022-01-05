package com.example.carpark.service.impl;

import com.example.carpark.dto.CarDto;
import com.example.carpark.model.Car;
import com.example.carpark.repository.CarRepository;
import com.example.carpark.repository.ParkingLotRepository;
import com.example.carpark.service.ICarService;
import org.jetbrains.annotations.TestOnly;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {
    private CarRepository carRepository;
    private ModelMapper modelMapper;

    public CarService() {
    }

    public CarService(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    //get all cars
    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //add new car
    @Override
    public void addNewCar(CarDto carDto) {
        carRepository.save(this.mapToEntity(carDto));
    }

    //delete car by license plate
    @Override
    public void deleteCarByLicensePlate(String s) {
        carRepository.deleteById(s);
    }

    //check car existed
    @Override
    public boolean existsById(String s) {
        return carRepository.existsById(s);
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

    @TestOnly
    public void Test() {
        ModelMapper modelMapper = new ModelMapper();

        // setup
        TypeMap<CarDto, Car> propertyMapper = modelMapper.createTypeMap(CarDto.class, Car.class);
        // add deep mapping to flatten source's CarDto object into a single field in destination
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getParkId(), Car::setParkingLot)
        );

        // when map between different hierarchies
        CarDto carDto = new CarDto();
        carDto.setLicensePlate("123-4567");
        carDto.setCarColor("Red");
        carDto.setCarType("Honda");
        carDto.setCompany("Thanh Trung");
        carDto.setParkId(1L);
        System.out.println(carDto);

        System.out.println(modelMapper.map(carDto, Car.class));
    }

    public static void main(String[] args) {
        CarService carService = new CarService();
        carService.Test();
    }
}
