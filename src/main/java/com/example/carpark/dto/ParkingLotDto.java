package com.example.carpark.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ParkingLotDto implements Serializable {
    private Long parkId;
    private Long parkArea;
    private String parkName;
    private String parkPlace;
    private Long parkPrice;
    private String parkStatus;
    private List<CarDto> car;
}
