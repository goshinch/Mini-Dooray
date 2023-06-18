package com.nhnacademy.minidoorayuserapi.user.dto;

import com.nhnacademy.minidoorayuserapi.user.entity.User;
import lombok.Data;

@Data
public class UserDetailsDto {
    private Long id;
    private String userId;
    private String name;
    private String password;
    private String email;
    private User.UserStatus status;
    private User.Role role;
}
