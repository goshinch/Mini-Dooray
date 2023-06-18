package com.nhnacademy.minidoorayuserapi.user.dto;

import com.nhnacademy.minidoorayuserapi.user.entity.User;
import lombok.Data;

@Data
public class UserSampleDto {
    private Long id;
    private String userId;
    private String password;
    private User user;
}
