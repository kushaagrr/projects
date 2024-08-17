package dev.studenterp.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotEmpty(message = "username is mandatory")
        @Size(min = 5, max = 32, message = "username must be between 5 to 32 character")
        String username,
        @NotBlank(message = "password is mandatory")
        @Size(min = 8, max = 50, message = "Password must be between 8 to 50 character")
        String password
) {}