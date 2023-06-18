//package com.nhnacademy.minidoorayuserapi.advisor.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nhnacademy.minidoorayuserapi.user.controller.UserController;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserSignUpRequest;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//@WebMvcTest(ExceptionAdvisorController.class)
//class ExceptionAdvisorControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @MockBean
//    UserController controller;
//
//    @Test
//    void handleValidationExceptions() throws Exception {
//        UserSignUpRequest signUpRequest = new UserSignUpRequest();
//        signUpRequest.setEmail("");
//        signUpRequest.setName("abc");
//        signUpRequest.setPassword("password");
//        signUpRequest.setUserId("abc");
//
//        String json = objectMapper.writeValueAsString(signUpRequest);
//
//        mockMvc.perform(post("/account/signUp")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json))
//            .andExpect(status().isBadRequest());
//    }
//}
