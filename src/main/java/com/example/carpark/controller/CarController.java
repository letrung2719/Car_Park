package com.example.carpark.controller;

import com.example.carpark.dto.CarDto;
import com.example.carpark.model.Car;
import com.example.carpark.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car-park")
public class CarController {
    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car/list")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car/get/{licensePlate}")
    public ResponseEntity<CarDto> getCarByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
        return carService.getCarByLicensePlate(licensePlate);
    }

    @PostMapping("/car/add")
    public ResponseEntity<Car> addNewCar(@ModelAttribute CarDto carDto) {
        return carService.addNewCar(carDto);
    }

    @DeleteMapping("/car/delete/{licensePlate}")
    public ResponseEntity<Map<String, Boolean>> deleteParkingLotById(@PathVariable("licensePlate") String licensePlate) {
        return carService.deleteCarByLicensePlate(licensePlate);
    }
}
