package com.nhnacademy.minidoorayuserapi.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.nhnacademy.minidoorayuserapi.user.entity.UserType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserSignUpRequest {
    @Length(max = 50)
    private String userId;

    @Length(max = 50)
    private String name;

    @Length(max = 100)
    private String password;

    @Email
    @NotBlank
    @Length(max = 100)
    private String email;

    private UserType.Type type;
}
