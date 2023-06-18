//package com.nhnacademy.minidoorayuserapi.user.controller;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nhnacademy.minidoorayuserapi.user.dto.*;
//import com.nhnacademy.minidoorayuserapi.user.entity.User;
//import com.nhnacademy.minidoorayuserapi.user.service.UserServiceImpl;
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    UserServiceImpl userService;
//
//    @Test
//    void existPassword() throws Exception {
//        UserPasswordRequest passwordRequest = new UserPasswordRequest();
//        passwordRequest.setUser_id("id");
//        String jsonBody = new ObjectMapper().writeValueAsString(passwordRequest);
//
//        UserPasswordDto password = new UserPasswordDto();
//        password.setPassword("password");
//        given(userService.findUserPassword("id")).willReturn(password);;
//
//        mockMvc.perform(post("/account/password")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonBody))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.password", equalTo("password")));
//
//        verify(userService, times(1)).findUserPassword("id");
//    }
//
//    @Test
//    void testFindUserDetails() throws Exception {
//        UserDetailsDto userDetailsDto = new UserDetailsDto();
//        userDetailsDto.setId(1L);
//        userDetailsDto.setPassword("password");
//        userDetailsDto.setEmail("email@email.com");
//        userDetailsDto.setUserId("id");
//        userDetailsDto.setStatus(User.UserStatus.ACTIVITY);
//        given(userService.findUserDetailsByUserId("id")).willReturn(userDetailsDto);;
//
//        mockMvc.perform(get("/account/users/{id}", "id"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.password", equalTo(userDetailsDto.getPassword())))
//            .andExpect(jsonPath("$.email", equalTo(userDetailsDto.getEmail())))
//            .andExpect(jsonPath("$.userId", equalTo(userDetailsDto.getUserId())));
//
//        verify(userService, times(1))
//            .findUserDetailsByUserId(userDetailsDto.getUserId());
//    }
//
//    @Test
//    void signUp() throws Exception {
//        UserSignUpRequest requestBody = new UserSignUpRequest();
//        requestBody.setUserId("id");
//        requestBody.setName("test");
//        requestBody.setPassword("password");
//        requestBody.setEmail("email@nhn.com");
//
//        String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
//
//        given(userService.signUp(requestBody)).willReturn(requestBody);
//
//        mockMvc.perform(post("/account/signUp")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(jsonBody))
//            .andExpect(status().isCreated())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//        verify(userService, times(1)).signUp(requestBody);
//    }
//
//    @Test
//    void verifyEmailTest() throws Exception {
//        SocialLoginEmailVerifyDto requestBody = new SocialLoginEmailVerifyDto();
//        requestBody.setEmail("email@nhn.com");
//
//        UserDto userDetailsDto = new UserDto();
//        userDetailsDto.setId(1L);
//        userDetailsDto.setName("test");
//        userDetailsDto.setEmail("email@email.com");
//        userDetailsDto.setStatus(User.UserStatus.ACTIVITY);
//
//        given(userService.findByUserDetailsByEmail(requestBody))
//            .willReturn(userDetailsDto);
//
//        String json = new ObjectMapper().writeValueAsString(requestBody);
//
//        mockMvc.perform(post("/account/email")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.email")
//                .value(userDetailsDto.getEmail()));
//    }
//
//    @Test
//    void findJoinedAllUserTest() throws Exception {
//        Long currentUserNo = 100L;
//        Pageable pageable = PageRequest.of(0, 3);
//
//        given(userService.findJoinedAllUser(currentUserNo, pageable))
//            .willReturn(
//                new PageImpl<>(new ArrayList<>(), pageable, 3)
//            );
//
//        mockMvc.perform(get("/account")
//                .param("userNo", "100")
//                .param("size", "3")
//                .param("page", "0")
//            )
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.users").isEmpty())
//            .andExpect(jsonPath("$.hasNext").value(false))
//            .andExpect(jsonPath("$.hasPrevious").value(false));
//    }
//}
//
