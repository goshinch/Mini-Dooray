package com.nhnacademy.minidoorayuserapi.user.dto;

import lombok.Data;

@Data
public class UserBasicDto {
    private Long id;

    private String name;

    private String email;
}
