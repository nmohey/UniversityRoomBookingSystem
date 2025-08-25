package com.sprints.UniversityRoomBookingSystem.controller;

import com.sprints.UniversityRoomBookingSystem.dto.RoomDto;
import com.sprints.UniversityRoomBookingSystem.dto.RoomFeatureDto;
import com.sprints.UniversityRoomBookingSystem.service.roomfeature.RoomFeatureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roomfeature")
public class RoomFeatureController {

    private final RoomFeatureService roomFeatureService;

    @Autowired
    public RoomFeatureController(RoomFeatureService roomFeatureService) {
        this.roomFeatureService = roomFeatureService;
    }

    @PostMapping
    public ResponseEntity<?> addFeature(@Valid @RequestBody RoomFeatureDto roomFeatureDto){
        try {
            RoomFeatureDto savedFeature = roomFeatureService.addFeature(roomFeatureDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFeature);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to create feature: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllFeatures(){
        try {
            List<RoomFeatureDto> features = roomFeatureService.getAllFeatures();
            return ResponseEntity.ok(features);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve features: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeatureById(@PathVariable Long id){
        try {
            RoomFeatureDto feature = roomFeatureService.getFeatureById(id);
            if (feature == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Feature not found with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(feature);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve feature: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeature(@PathVariable Long id, @Valid @RequestBody RoomFeatureDto roomFeatureDto) {
        try {
            RoomFeatureDto updatedFeature = roomFeatureService.updateFeature(id, roomFeatureDto);
            if (updatedFeature == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Feature not found or could not be updated with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(updatedFeature);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to update feature: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeature(@PathVariable Long id){
        try {
            String result = roomFeatureService.deleteFeature(id);
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
            error.put("message", "Failed to delete feature: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
