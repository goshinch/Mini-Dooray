package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.domain.SignupForm;
import com.nhnacademy.gateway.service.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupDefault(@ModelAttribute SignupForm signupForm) {
        signupForm.setType(SignupForm.Type.DEFAULT);
        signupService.signup(signupForm);
        return "redirect:/";
    }
}
