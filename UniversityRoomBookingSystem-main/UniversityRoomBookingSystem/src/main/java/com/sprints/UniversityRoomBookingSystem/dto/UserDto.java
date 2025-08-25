package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.Booking;
import com.sprints.UniversityRoomBookingSystem.model.Department;
import com.sprints.UniversityRoomBookingSystem.model.Holiday;
import com.sprints.UniversityRoomBookingSystem.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Department department;

    private List<Role> roles = new ArrayList<>();

    private List<Booking> bookings = new ArrayList<>();

    private List<Holiday> holidays = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(String name, String email, String password, LocalDateTime createdAt, Department department, List<Role> roles, List<Booking> bookings, List<Holiday> holidays) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.department = department;
        this.roles = roles;
        this.bookings = bookings;
        this.holidays = holidays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }
}
