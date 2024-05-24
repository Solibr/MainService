package ru.gashev.test.advenjineering.projectmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationData {

    private String username;
    private String password;
    private String passwordConfirmation;

}
