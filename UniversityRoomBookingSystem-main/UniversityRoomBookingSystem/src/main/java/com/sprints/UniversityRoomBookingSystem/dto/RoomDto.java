package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.Booking;
import com.sprints.UniversityRoomBookingSystem.model.Building;
import com.sprints.UniversityRoomBookingSystem.model.RoomFeature;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class RoomDto {

    private String room_no;

    private int capacity;

    private Building building;

    private List<RoomFeature> features = new ArrayList<>();

    public RoomDto() {
    }

    public RoomDto(String room_no, int capacity, Building building, List<RoomFeature> features) {
        this.room_no = room_no;
        this.capacity = capacity;
        this.building = building;
        this.features = features;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<RoomFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<RoomFeature> features) {
        this.features = features;
    }
}
