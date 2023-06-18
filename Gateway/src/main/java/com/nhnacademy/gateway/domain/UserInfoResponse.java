package com.nhnacademy.gateway.domain;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String userId;
    private String name;
    private String email;
    private String login;
    private String password;
    private String status;
    private Role role;
}
