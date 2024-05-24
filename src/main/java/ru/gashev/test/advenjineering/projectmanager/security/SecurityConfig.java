package ru.gashev.test.advenjineering.projectmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.ResponseStatusException;
import ru.gashev.test.advenjineering.projectmanager.exception.UserNotFoundException;
import ru.gashev.test.advenjineering.projectmanager.repository.UserRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        .requestMatchers("/projects/new/**").hasAuthority("ADMIN")
                        .requestMatchers("/projects/{id}/update/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/projects/**").authenticated()
                        .requestMatchers("/projects/**").hasAuthority("ADMIN")

                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                ;

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return (username) -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
