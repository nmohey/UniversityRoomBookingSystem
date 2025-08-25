package com.sprints.UniversityRoomBookingSystem.dto;

import com.sprints.UniversityRoomBookingSystem.model.User;

import java.util.List;

public class DepartmentDto {

    private String name;

    private List<User> users;

    public DepartmentDto() {
    }

    public DepartmentDto(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
