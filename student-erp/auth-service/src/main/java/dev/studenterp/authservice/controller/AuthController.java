package dev.studenterp.authservice.controller;

import dev.studenterp.authservice.dto.request.AuthRequest;
import dev.studenterp.authservice.dto.request.UserRequest;
import dev.studenterp.authservice.dto.response.AuthResponse;
import dev.studenterp.authservice.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserAuthService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        return userService
                .signin(authRequest)
                .map(token -> {
                    System.out.println(token);
                    return ResponseEntity.ok(new AuthResponse(token));
                });
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<AuthResponse>> signup(@RequestBody UserRequest userRequest) {
        return userService
                .signup(userRequest)
                .map(token -> ResponseEntity.ok(new AuthResponse(token)));
    }

}
