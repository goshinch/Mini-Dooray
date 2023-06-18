package com.nhnacademy.minidoorayuserapi.user.dto;

import com.nhnacademy.minidoorayuserapi.user.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private User.UserStatus status;
}
