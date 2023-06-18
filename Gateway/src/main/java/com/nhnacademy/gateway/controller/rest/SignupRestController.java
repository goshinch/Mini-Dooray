package com.nhnacademy.gateway.controller.rest;

import com.nhnacademy.gateway.domain.SignupForm;
import com.nhnacademy.gateway.service.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignupRestController {
    private final SignupService signupService;

    public SignupRestController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupForm> signupDefault(@RequestBody SignupForm signupForm) {
        signupForm.setType(SignupForm.Type.DEFAULT);
        return signupService.signup(signupForm);
    }
}
