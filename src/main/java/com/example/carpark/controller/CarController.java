package com.example.carpark.controller;

import com.example.carpark.dto.CarDto;
import com.example.carpark.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private ICarService carService;

    @GetMapping("/view")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/add")
    public String addNewCar(@ModelAttribute CarDto carDto) {
        carService.addNewCar(carDto);
        return "Add new car successfully!";
    }

    @GetMapping("/delete/{licensePlate}")
    public String deleteParkingLotById(@PathVariable ("licensePlate") String licensePlate) {
        if (carService.existsById(licensePlate)){
            carService.deleteCarByLicensePlate(licensePlate);
            return "Delete car successfully!";
        }else{
            return "This id is not existed!";
        }
    }
}
