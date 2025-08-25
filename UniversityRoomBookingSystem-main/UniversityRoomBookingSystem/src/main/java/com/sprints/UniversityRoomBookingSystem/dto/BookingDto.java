package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;
import com.sprints.UniversityRoomBookingSystem.model.Room;
import com.sprints.UniversityRoomBookingSystem.model.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto {

    @NotBlank(message = "Purpose is required")
    private String purpose;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDateTime start_date;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime end_date;

    private BookingStatus status;

    @NotNull(message = "Room is required")
    private Room room;

    @NotNull(message = "User is required")
    private User user;

    public BookingDto() {
    }

    public BookingDto(String purpose, LocalDateTime start_date, LocalDateTime end_date,
                      BookingStatus status, Room room, User user) {
        this.purpose = purpose;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.room = room;
        this.user = user;
    }
}
