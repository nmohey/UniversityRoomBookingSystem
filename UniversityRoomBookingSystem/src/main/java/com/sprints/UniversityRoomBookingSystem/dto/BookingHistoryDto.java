package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.Booking;
import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class BookingHistoryDto {

    private LocalDateTime timestamp;

    private BookingStatus status;

    private String performedBy;

    private Booking booking;

    public BookingHistoryDto() {
    }

    public BookingHistoryDto(LocalDateTime timestamp, BookingStatus status, String performedBy, Booking booking) {
        this.timestamp = timestamp;
        this.status = status;
        this.performedBy = performedBy;
        this.booking = booking;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
