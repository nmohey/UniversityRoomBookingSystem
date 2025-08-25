package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.Booking;
import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;

import java.time.LocalDateTime;

public class BookingHistoryDto {
    private LocalDateTime timestamp;
    private BookingStatus status;
    private String changedByName;
    private Booking booking;

    public BookingHistoryDto() {}

    public BookingHistoryDto(LocalDateTime timestamp, BookingStatus status, String changedByName, Booking booking) {
        this.timestamp = timestamp;
        this.status = status;
        this.changedByName = changedByName;
        this.booking = booking;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public String getChangedByName() { return changedByName; }
    public void setChangedByName(String changedByName) { this.changedByName = changedByName; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
}
