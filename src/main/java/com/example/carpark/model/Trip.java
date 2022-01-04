package com.example.carpark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", nullable = false)
    private Long tripId;

    @Column(name = "booked_ticket_number", nullable = false)
    private Integer bookedTicketNumber;

    @Column(name = "car_type", nullable = false, length = 50)
    private String carType;

    @Column(name = "departure_date", nullable = false)
    private Date departureDate;

    @Column(name = "departure_time", nullable = false)
    private Time departureTime;

    @Column(name = "destination", nullable = false, length = 50)
    private String destination;

    @Column(name = "driver", nullable = false, length = 50)
    private String driver;

    @Column(name = "maximum_online_ticket_number", nullable = false)
    private Integer maximumOnlineTicketNumber;

    @OneToMany(mappedBy = "trip", orphanRemoval = true)
    private List<BookingOffice> bookingOffice = new ArrayList<>();

    @OneToMany(mappedBy = "trip", orphanRemoval = true)
    private List<Ticket> ticket = new ArrayList<>();

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public List<BookingOffice> getBookingOffice() {
        return bookingOffice;
    }

    public void setBookingOffice(List<BookingOffice> bookingOffice) {
        this.bookingOffice = bookingOffice;
    }

    public Integer getMaximumOnlineTicketNumber() {
        return maximumOnlineTicketNumber;
    }

    public void setMaximumOnlineTicketNumber(Integer maximumOnlineTicketNumber) {
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getBookedTicketNumber() {
        return bookedTicketNumber;
    }

    public void setBookedTicketNumber(Integer bookedTicketNumber) {
        this.bookedTicketNumber = bookedTicketNumber;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
