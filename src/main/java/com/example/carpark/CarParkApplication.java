package com.example.carpark;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
public class CarParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarParkApplication.class, args);
    }

}
