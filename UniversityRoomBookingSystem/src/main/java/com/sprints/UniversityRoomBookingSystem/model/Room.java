package com.sprints.UniversityRoomBookingSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Room Number is required")
    private String room_no;

    @NotBlank(message = "Room Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 100, message = "Capacity must be at most 100")
    private String capacity;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToMany
    @JoinTable(
            name = "room_features",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name="feature_id")
    )
    private List<RoomFeature> features;

    public Room() {
    }

    public Room(String room_no, String capacity, Building building, List<RoomFeature> features) {
        this.room_no = room_no;
        this.capacity = capacity;
        this.building = building;
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
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
