package dev.studenterp.authservice.service;

import dev.studenterp.authservice.dto.request.UserRequest;
import dev.studenterp.authservice.repository.UserRepository;
import dev.studenterp.authservice.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .map(user ->
                        User.withUsername(user.getUsername())
                                .password(user.getPassword())
                                .disabled(!user.isEnabled())
                                .roles(user.getRole().name())
                                .build())
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")));
    }

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
}

