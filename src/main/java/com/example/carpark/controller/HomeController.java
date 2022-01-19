package com.example.carpark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/","car-park"})
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "<h1>Welcome to CarPark Application!</h1>";
    }
}
