package com.my.library.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Component
@Validated
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;
    @Digits(integer = 10, fraction = 0)
    private long mobile;
    @Email
    @NotEmpty
    private String email;
    private boolean status;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String password;
    private int otp;
}
