package com.sprints.UniversityRoomBookingSystem.controller;

import com.sprints.UniversityRoomBookingSystem.dto.DepartmentDto;
import com.sprints.UniversityRoomBookingSystem.dto.HolidayDto;
import com.sprints.UniversityRoomBookingSystem.service.holiday.HolidayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/holiday")
public class HolidayController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping
    public ResponseEntity<?> addHoliday(@Valid @RequestBody HolidayDto holidayDto){
        try {
            HolidayDto savedHoliday = holidayService.addHoliday(holidayDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHoliday);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to create holiday: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllHolidays(){
        try {
            List<HolidayDto> holidays = holidayService.getAllHolidays();
            return ResponseEntity.ok(holidays);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve holidays: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHolidayById(@PathVariable Long id){
        try {
            HolidayDto holiday = holidayService.getHolidayById(id);
            if (holiday == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Holiday not found with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(holiday);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve holiday: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHoliday(@PathVariable Long id, @Valid @RequestBody HolidayDto holidayDto) {
        try {
            HolidayDto updatedHoliday = holidayService.updateHoliday(id, holidayDto);
            if (updatedHoliday == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Holiday not found or could not be updated with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(updatedHoliday);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to update holiday: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHoliday(@PathVariable Long id){
        try {
            String result = holidayService.deleteHoliday(id);
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
            error.put("message", "Failed to delete holiday: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
