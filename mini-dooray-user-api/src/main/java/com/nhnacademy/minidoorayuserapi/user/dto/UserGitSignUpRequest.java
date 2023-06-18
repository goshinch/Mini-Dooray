package com.nhnacademy.minidoorayuserapi.user.dto;

import com.nhnacademy.minidoorayuserapi.user.entity.UserType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserGitSignUpRequest {
    @Length(max = 50)
    private String name;

    @Email
    @NotBlank
    @Length(max = 100)
    private String email;
}
