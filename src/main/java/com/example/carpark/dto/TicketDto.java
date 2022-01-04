package com.example.carpark.dto;

import com.example.carpark.dto.CarDto;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
public class TicketDto implements Serializable {
    private Long ticketId;
    private Time bookingTime;
    private String customerName;
    private String licensePlate;
    private Long tripId;
}
