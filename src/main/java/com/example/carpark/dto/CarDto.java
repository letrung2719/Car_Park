package com.example.carpark.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarDto implements Serializable {
    private String licensePlate;
    private String carColor;
    private String carType;
    private String company;
    private Long parkId;
    private List<TicketDto> ticket;
}
