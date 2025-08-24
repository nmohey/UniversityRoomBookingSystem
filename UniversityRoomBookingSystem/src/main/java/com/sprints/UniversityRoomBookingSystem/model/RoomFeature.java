package com.sprints.UniversityRoomBookingSystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_feature")
public class RoomFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feature_name")
    private String feature;

    @ManyToMany(mappedBy = "features")
    private List<Room> rooms = new ArrayList<>();

    public RoomFeature() {
    }

    public RoomFeature(String feature_name, List<Room> rooms) {
        this.feature = feature_name;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
