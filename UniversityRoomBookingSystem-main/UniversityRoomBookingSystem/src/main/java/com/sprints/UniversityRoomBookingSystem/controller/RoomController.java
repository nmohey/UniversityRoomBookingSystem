package com.sprints.UniversityRoomBookingSystem.controller;

import com.sprints.UniversityRoomBookingSystem.dto.HolidayDto;
import com.sprints.UniversityRoomBookingSystem.dto.RoomDto;
import com.sprints.UniversityRoomBookingSystem.service.room.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@Valid @RequestBody RoomDto roomDto){
        try {
            RoomDto savedRoom = roomService.addRoom(roomDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to create room: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms(){
        try {
            List<RoomDto> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve rooms: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id){
        try {
            RoomDto room = roomService.getRoomById(id);
            if (room == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Room not found with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve room: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDto roomDto) {
        try {
            RoomDto updatedRoom = roomService.updateRoom(id, roomDto);
            if (updatedRoom == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Room not found or could not be updated with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(updatedRoom);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to update room: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id){
        try {
            String result = roomService.deleteRoom(id);
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
            error.put("message", "Failed to delete room: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
