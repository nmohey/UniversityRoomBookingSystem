package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.Room;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class BuildingDto {

    private String name;

    private int building_no;

    public BuildingDto() {
    }

    public BuildingDto(String name, int building_no) {
        this.name = name;
        this.building_no = building_no;
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
}
