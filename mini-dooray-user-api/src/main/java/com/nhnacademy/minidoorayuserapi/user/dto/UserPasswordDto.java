package com.nhnacademy.minidoorayuserapi.user.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserPasswordDto {
    @NotBlank
    @Length(min = 1)
    private String password;
}
