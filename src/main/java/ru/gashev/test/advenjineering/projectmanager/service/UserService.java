package ru.gashev.test.advenjineering.projectmanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gashev.test.advenjineering.projectmanager.dto.UserRegistrationData;
import ru.gashev.test.advenjineering.projectmanager.entity.Authority;
import ru.gashev.test.advenjineering.projectmanager.entity.AuthorityType;
import ru.gashev.test.advenjineering.projectmanager.entity.User;
import ru.gashev.test.advenjineering.projectmanager.exception.PasswordsNotMatchException;
import ru.gashev.test.advenjineering.projectmanager.exception.UsernameAlreadyExistsException;
import ru.gashev.test.advenjineering.projectmanager.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerNewUser(UserRegistrationData userRegistrationData) {

        if (userRepository.findByUsername(userRegistrationData.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException("Username already taken");

        if (!userRegistrationData.getPassword().equals(userRegistrationData.getPasswordConfirmation()))
            throw new PasswordsNotMatchException("Passwords are not matching");

        User user = new User(
                userRegistrationData.getUsername(),
                passwordEncoder.encode(userRegistrationData.getPassword()),
                List.of(new Authority(AuthorityType.USER))
        );
        log.info("Persisting new user with username: {}", userRegistrationData.getUsername());
        userRepository.save(user);
    }



}
