package com.example.carpark.controller;

import com.example.carpark.dto.CarDto;
import com.example.carpark.model.Car;
import com.example.carpark.service.ICarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {
    private final ICarService carService;

    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("/view")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/add")
    public Car addNewCar(@ModelAttribute CarDto carDto) {
        return carService.addNewCar(carDto);
    }

    @GetMapping("/delete/{licensePlate}")
    public String deleteParkingLotById(@PathVariable("licensePlate") String licensePlate) {
        return carService.deleteCarByLicensePlate(licensePlate);
    }
}
