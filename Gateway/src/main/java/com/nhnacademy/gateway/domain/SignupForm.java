package com.nhnacademy.gateway.domain;

import lombok.Data;

@Data
public class SignupForm {
    private String userId;
    private String name;
    private String password;
    private String email;
    private Type type;
    public enum Type {
        DEFAULT, GIT
    }
}
