package com.sprints.UniversityRoomBookingSystem.service.user;

import com.sprints.UniversityRoomBookingSystem.dto.UserDto;
import com.sprints.UniversityRoomBookingSystem.model.User;
import com.sprints.UniversityRoomBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getDepartment(),
                user.getRoles(),
                user.getBookings(),
                user.getHolidays()
        );
    }

    private User mapToEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(dto.getCreatedAt());
        user.setDepartment(dto.getDepartment());
        user.setRoles(dto.getRoles());
        user.setBookings(dto.getBookings());
        user.setHolidays(dto.getHolidays());
        return user;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::mapToDto).orElse(null);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setDepartment(userDto.getDepartment());
            user.setRoles(userDto.getRoles());
            user.setBookings(userDto.getBookings());
            user.setHolidays(userDto.getHolidays());
            User updatedUser = userRepository.save(user);
            return mapToDto(updatedUser);
        }
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted successfully.";
        }
        return "User with id " + id + " not found.";
    }
}
