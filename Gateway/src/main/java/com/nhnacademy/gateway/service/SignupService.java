package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.config.AccountServerProperties;
import com.nhnacademy.gateway.domain.GitSignForm;
import com.nhnacademy.gateway.domain.SignupForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SignupService {
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final String signup;
    private final AccountServerProperties accountServerProperties;

    public SignupService(RestTemplate restTemplate, PasswordEncoder passwordEncoder, @Value("${account.api.url.signup}") String signup, AccountServerProperties accountServerProperties) {
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
        this.signup = signup;
        this.accountServerProperties = accountServerProperties;
    }

    public ResponseEntity<SignupForm> signup(SignupForm signupForm) {
        signupForm.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<SignupForm> signEntity = new HttpEntity<>(signupForm, headers);
        ResponseEntity<SignupForm> responseSignUp = restTemplate.exchange(
                accountServerProperties.accountServerProvider() + signup,
                HttpMethod.POST,
                signEntity,
                SignupForm.class
        );
        return responseSignUp;
    }
}
