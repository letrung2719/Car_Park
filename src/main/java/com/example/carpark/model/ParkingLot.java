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
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "park_id", nullable = false)
    private Long parkId;

    @Column(name = "park_area", nullable = false)
    private Long parkArea;

    @Column(name = "park_name", nullable = false, length = 50)
    private String parkName;

    @Column(name = "park_place", nullable = false, length = 50)
    private String parkPlace;

    @Column(name = "park_price", nullable = false)
    private Long parkPrice;

    @Column(name = "park_status", nullable = false, length = 50)
    private String parkStatus;

    @OneToMany(mappedBy = "parkingLot", orphanRemoval = true)
    private List<Car> car = new ArrayList<>();

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public String getParkStatus() {
        return parkStatus;
    }

    public void setParkStatus(String parkStatus) {
        this.parkStatus = parkStatus;
    }

    public Long getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(Long parkPrice) {
        this.parkPrice = parkPrice;
    }

    public String getParkPlace() {
        return parkPlace;
    }

    public void setParkPlace(String parkPlace) {
        this.parkPlace = parkPlace;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Long getParkArea() {
        return parkArea;
    }

    public void setParkArea(Long parkArea) {
        this.parkArea = parkArea;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }
}
