package dev.studenterp.authservice.controller;

import dev.studenterp.authservice.dto.request.UserRequest;
import dev.studenterp.authservice.dto.response.AuthResponse;
import dev.studenterp.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;


    @PostMapping("/login")
    public Mono<String> login() {
        return Mono.just("hello at login route");
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<AuthResponse>> signup(@RequestBody UserRequest userRequest) {
        return userService
                .signup(userRequest)
                .map(token -> ResponseEntity.ok(new AuthResponse(token)));
    }

}
