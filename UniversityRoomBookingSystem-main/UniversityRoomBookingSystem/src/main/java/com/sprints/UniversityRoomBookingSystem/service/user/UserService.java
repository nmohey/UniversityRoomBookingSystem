package com.sprints.UniversityRoomBookingSystem.service.user;

import com.sprints.UniversityRoomBookingSystem.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto addUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto getUserById(Long id);

    public UserDto updateUser(Long id, UserDto userDto);

    public String deleteUser(Long id);
}
