package dev.studenterp.authservice.dto.request;

import dev.studenterp.authservice.utils.ROLE;

public record UserRequest(String username, String password, ROLE role, String email) {
}
