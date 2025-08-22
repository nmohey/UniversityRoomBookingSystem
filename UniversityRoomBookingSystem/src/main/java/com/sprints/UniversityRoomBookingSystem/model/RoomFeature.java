package com.sprints.UniversityRoomBookingSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room_feature")
public class RoomFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feature_name;

    public RoomFeature() {
    }

    public RoomFeature(String feature_name) {
        this.feature_name = feature_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }
}
