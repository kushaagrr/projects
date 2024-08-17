package dev.studenterp.authservice.service;

import dev.studenterp.authservice.dto.request.AuthRequest;
import dev.studenterp.authservice.dto.request.UserRequest;
import dev.studenterp.authservice.repository.UserRepository;
import dev.studenterp.authservice.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Mono<String> signup(UserRequest userRequest) {
        dev.studenterp.authservice.entity.User u =
                new dev.studenterp.authservice.entity.User(
                        userRequest.username(),
                        passwordEncoder.encode(userRequest.password()),
                        userRequest.email(),
                        userRequest.role()
                );

        return userRepository
                .save(u)
                .mapNotNull(user -> jwtService.generateToken(user.getUsername()));
    }

    public Mono<String> signin(AuthRequest authRequest) {
        return userRepository
                .findByUsername(authRequest.username())
                .flatMap(user -> {
                    if(passwordEncoder.matches(authRequest.password(), user.getPassword())){
                        if (!user.isAccountNonLocked() || !user.isEnabled()) {
                            return Mono.error(new RuntimeException("Account is locked or disabled"));
                        }
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        authRequest.username(),
                                        null,
                                        user.getAuthorities()
                                );

                        return Mono.just(jwtService.generateToken(user.getUsername()));
                    }
                    return Mono.error(new RuntimeException("Invalid credentials"));
                });
    }
}
