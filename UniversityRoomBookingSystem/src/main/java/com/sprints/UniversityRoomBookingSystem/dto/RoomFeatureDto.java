package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.Room;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

public class RoomFeatureDto {

    private String feature;

    private List<Room> rooms = new ArrayList<>();

    public RoomFeatureDto() {
    }

    public RoomFeatureDto(String feature, List<Room> rooms) {
        this.feature = feature;
        this.rooms = rooms;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
