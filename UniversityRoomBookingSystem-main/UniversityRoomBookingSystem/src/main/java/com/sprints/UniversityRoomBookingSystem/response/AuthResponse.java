package com.sprints.UniversityRoomBookingSystem.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String token; // For JWT
    private String message;

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(boolean success, String message) {
        this.message = message;
    }

}
