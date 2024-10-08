package dev.studenterp.authservice.service;

import dev.studenterp.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;

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


}

