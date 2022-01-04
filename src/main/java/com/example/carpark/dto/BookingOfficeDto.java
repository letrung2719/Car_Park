package com.example.carpark.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class BookingOfficeDto implements Serializable {
    private Long officeId;
    private Date endContractDeadline;
    private String officeName;
    private String officePhone;
    private String officePlace;
    private Long officePrice;
    private Date startContractDeadline;
    private Long tripId;
}
