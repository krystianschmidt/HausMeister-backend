package com.example.spuelmaschine.plugins.security;

import com.example.spuelmaschine.domain.user.UserApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserApplication userApplication;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Verwendung von `httpBasic` und `csrf` ohne veraltete Methoden
        http
                .csrf(AbstractHttpConfigurer::disable) // Modernere Methode zum Deaktivieren von CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/available").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .alwaysRemember(true)
                        .userDetailsService(userApplication)
                        .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days
                        .key("AbcdefghiJklmNoPqRstUvXyz")
                )
                .httpBasic(httpBasic -> {}); // Die modernisierte httpBasic()-Methode

        return http.build();
    }
}
