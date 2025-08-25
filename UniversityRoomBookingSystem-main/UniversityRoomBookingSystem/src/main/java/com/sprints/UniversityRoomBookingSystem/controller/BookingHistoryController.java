package com.sprints.UniversityRoomBookingSystem.controller;

import com.sprints.UniversityRoomBookingSystem.dto.BookingDto;
import com.sprints.UniversityRoomBookingSystem.dto.BookingHistoryDto;
import com.sprints.UniversityRoomBookingSystem.service.bookinghistory.BookingHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookinghistory")
public class BookingHistoryController {

    private final BookingHistoryService bookingHistoryService;

    @Autowired
    public BookingHistoryController(BookingHistoryService bookingHistoryService) {
        this.bookingHistoryService = bookingHistoryService;
    }

    @PostMapping
    public ResponseEntity<?> addBookingHistory(@Valid @RequestBody BookingHistoryDto bookingHistoryDto){
        try {
            BookingHistoryDto savedHistory = bookingHistoryService.addBookingHistory(bookingHistoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHistory);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to create booking history: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBookingHistories(){
        try {
            List<BookingHistoryDto> histories = bookingHistoryService.getAllBookingHistories();
            return ResponseEntity.ok(histories);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve booking histories: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingHistoryById(@PathVariable Long id){
        try {
            BookingHistoryDto history = bookingHistoryService.getBookingHistoryById(id);
            if (history == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Booking history not found with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to retrieve booking history: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookingHistory(@PathVariable Long id, @Valid @RequestBody BookingHistoryDto bookingHistoryDto) {
        try {
            BookingHistoryDto updatedHistory = bookingHistoryService.updateBookingHistory(id, bookingHistoryDto);
            if (updatedHistory == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Booking history not found or could not be updated with ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(updatedHistory);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to update booking history: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingHistory(@PathVariable Long id){
        try {
            String result = bookingHistoryService.deleteBookingHistory(id);
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
            error.put("message", "Failed to delete booking history: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
