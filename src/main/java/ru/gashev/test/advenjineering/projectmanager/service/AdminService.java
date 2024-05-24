package ru.gashev.test.advenjineering.projectmanager.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.gashev.test.advenjineering.projectmanager.entity.Authority;
import ru.gashev.test.advenjineering.projectmanager.entity.AuthorityType;
import ru.gashev.test.advenjineering.projectmanager.entity.User;
import ru.gashev.test.advenjineering.projectmanager.repository.UserRepository;

import java.util.*;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getResultOfGrantAdminRoleToUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            return "user not found";
        User user = optionalUser.get();
        if (user.getAuthorities().contains(new Authority(AuthorityType.ADMIN)))
            return "this user already has admin role";

        user.getAuthorities().add(new Authority(AuthorityType.ADMIN));

        userRepository.save(user);
        return "admin role granted";
    }


    public String getResultOfDepriveAdminRoleToUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            return "user not found";
        User user = optionalUser.get();
        if (!user.getAuthorities().contains(new Authority(AuthorityType.ADMIN)))
            return "this user already doesn't have admin role";

        user.getAuthorities().remove(new Authority(AuthorityType.ADMIN));

        userRepository.save(user);
        return "admin role deprived";
    }
}
