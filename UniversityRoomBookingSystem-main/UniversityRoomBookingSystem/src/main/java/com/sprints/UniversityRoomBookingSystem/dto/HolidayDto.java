package com.sprints.UniversityRoomBookingSystem.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class HolidayDto {

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public HolidayDto() {
    }

    public HolidayDto(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
