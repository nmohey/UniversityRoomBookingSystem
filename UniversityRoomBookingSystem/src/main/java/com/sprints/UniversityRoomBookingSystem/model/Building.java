package com.sprints.UniversityRoomBookingSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Building Number is required")
    private int building_no;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms = new ArrayList<>();

    public Building() {
    }

    public Building(String name, int building_no, List<Room> rooms) {
        this.name = name;
        this.building_no = building_no;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(int building_no) {
        this.building_no = building_no;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
