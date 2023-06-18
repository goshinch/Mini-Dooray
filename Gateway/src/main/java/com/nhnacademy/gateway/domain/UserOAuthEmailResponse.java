package com.nhnacademy.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserOAuthEmailResponse {
    private String email;
    private Boolean primary;
    private Boolean verified;
    private String visibility;
}
