package com.sprints.UniversityRoomBookingSystem.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import com.sprints.UniversityRoomBookingSystem.model.Role;
import lombok.Data;

@Data

public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Long departmentId;
    private List<Role> roles;

}
