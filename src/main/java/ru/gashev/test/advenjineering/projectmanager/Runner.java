package ru.gashev.test.advenjineering.projectmanager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.gashev.test.advenjineering.projectmanager.entity.Authority;
import ru.gashev.test.advenjineering.projectmanager.entity.AuthorityType;
import ru.gashev.test.advenjineering.projectmanager.entity.User;
import ru.gashev.test.advenjineering.projectmanager.repository.AuthorityRepository;
import ru.gashev.test.advenjineering.projectmanager.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        saveRoles();
        createAdmin();
    }

    private void saveRoles() {
        Set<AuthorityType> allAuthorityTypes = new HashSet<>(Arrays.asList(AuthorityType.values()));
        Set<AuthorityType> persistedAuthorityTypes = new HashSet<>();
        authorityRepository.findAll().forEach(authority -> persistedAuthorityTypes.add(authority.getAuthorityType()));
        allAuthorityTypes.removeAll(persistedAuthorityTypes);
        Set<Authority> authoritiesToAdd = allAuthorityTypes
                .stream().map(authorityType -> new Authority(authorityType)).collect(Collectors.toSet());
        authorityRepository.saveAll(authoritiesToAdd);
    }

    private void createAdmin() {

        if (userRepository.findByUsername("admin").isEmpty()) {
            String firstAdminPassword = UUID.randomUUID().toString();
            User firstAdmin = new User("admin", passwordEncoder.encode(firstAdminPassword),
                    List.of(new Authority(AuthorityType.USER), new Authority(AuthorityType.ADMIN)));
            userRepository.save(firstAdmin);
            System.out.println("\nFirst admin password: " + firstAdminPassword + "\n");
        } else {
            User firstAdmin = userRepository.findByUsername("admin").get();
            String firstAdminPassword = UUID.randomUUID().toString();
            firstAdmin.setPassword(passwordEncoder.encode(firstAdminPassword));
            firstAdmin.setAuthorities(new HashSet<>(List.of(
                    new Authority(AuthorityType.USER), new Authority(AuthorityType.ADMIN))));
            userRepository.save(firstAdmin);
            System.out.println("\nFirst admin password: " + firstAdminPassword + "\n");
        }
    }

}
