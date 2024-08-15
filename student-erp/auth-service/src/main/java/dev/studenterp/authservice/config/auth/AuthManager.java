package dev.studenterp.authservice.config.auth;

import dev.studenterp.authservice.service.UserService;
import dev.studenterp.authservice.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class AuthManager implements ReactiveAuthenticationManager {
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if(authentication.getCredentials() != null){
            String jwt = authentication.getCredentials().toString();
            String username = jwtService.extractUsername(jwt);
            return userService.findByUsername(username)
                    .mapNotNull(userDetails -> {
                        if (jwtService.validateToken(jwt, userDetails.getUsername())) {
                            return new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    userDetails.getAuthorities()
                            );
                        } else {
                            throw new BadCredentialsException("Invalid JWT token");
                        }
                    });
        }
        return Mono.empty();
    }

}
