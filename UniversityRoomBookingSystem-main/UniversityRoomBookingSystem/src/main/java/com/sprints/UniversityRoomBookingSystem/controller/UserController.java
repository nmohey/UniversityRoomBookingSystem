package com.sprints.UniversityRoomBookingSystem.controller;

import com.sprints.UniversityRoomBookingSystem.dto.RoomFeatureDto;
import com.sprints.UniversityRoomBookingSystem.dto.UserDto;
import com.sprints.UniversityRoomBookingSystem.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto){
        try {
            UserDto savedUser = userService.addUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to create user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            List<UserDto> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            UserDto user = userService.getUserById(id);
            if (user == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "User not found with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUser(id, userDto);
            if (updatedUser == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "User not found or could not be updated with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to update user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            String result = userService.deleteUser(id);
            if (result.contains("not found")) {
                Map<String, String> error = new HashMap<>();
                error.put("message", result);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            Map<String, String> success = new HashMap<>();
            success.put("message", result);
            return ResponseEntity.ok(success);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to delete user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
