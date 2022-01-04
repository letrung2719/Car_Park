package com.example.carpark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "license_plate", nullable = false, length = 50)
    private String licensePlate;

    @Column(name = "car_color", nullable = false, length = 11)
    private String carColor;

    @Column(name = "car_type", nullable = false, length = 50)
    private String carType;

    @Column(name = "company", nullable = false, length = 50)
    private String company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "park_id", nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "car", orphanRemoval = true)
    private List<Ticket> ticket = new ArrayList<>();

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
