package com.example.carpark.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class EmployeeDto implements Serializable {
    private Long employeeId;
    private String account;
    private String department;
    private String employeeAddress;
    private Date employeeBirthdate;
    private String employeeEmail;
    private String employeeName;
    private String employeePhone;
    private Character sex;
}
