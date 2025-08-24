package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;
import com.sprints.UniversityRoomBookingSystem.model.Room;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public class BookingDto {

    private String purpose;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    private BookingStatus status;

    private Room room;

    public BookingDto() {
    }

    public BookingDto(String purpose, LocalDateTime start_date, LocalDateTime end_date, BookingStatus status, Room room) {
        this.purpose = purpose;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.room = room;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
