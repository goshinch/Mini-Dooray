package com.nhnacademy.minidoorayuserapi.user.controller;

import com.nhnacademy.minidoorayuserapi.user.dto.*;
import com.nhnacademy.minidoorayuserapi.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findUserDetails() {
        List<UserDto> userDetailsList = userService.findUserDetails();
        return ResponseEntity.ok(userDetailsList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetailsDto> findUserDetails(@PathVariable("id") String userId){
        UserDetailsDto userSampleDto = userService.findUserDetailsByUserId(userId);
        return ResponseEntity.ok(userSampleDto);
    }

    @PostMapping("/password")
    public ResponseEntity<UserPasswordDto> existPassword(@Validated @RequestBody UserPasswordRequest requestBody){
        UserPasswordDto passwordDto = userService.findUserPassword(requestBody.getUser_id());
        return ResponseEntity.ok(passwordDto);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserSignUpRequest> signUp(@Validated @RequestBody UserSignUpRequest signUpRequest){
        UserSignUpRequest userSignUpRequest = userService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignUpRequest);
    }

    @PostMapping("/git/signUp")
    public ResponseEntity<UserGitSignUpRequest> gitSignUp(@Validated @RequestBody UserGitSignUpRequest gitSignUpRequest){
        UserGitSignUpRequest userGitSignUpRequest = userService.gitSignUp(gitSignUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userGitSignUpRequest);
    }

    @PostMapping("/email")
    public ResponseEntity<UserDto> verifyEmail(@Validated @RequestBody
                                       SocialLoginEmailVerifyDto socialLoginEmailVerify) {
        UserDto userDto = userService.findByUserDetailsByEmail(socialLoginEmailVerify);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/exist/emails")
    public ResponseEntity<UserExistDto> existEmail(@Validated @RequestParam("email") String socialLoginEmailVerify) {
        UserExistDto userExistDto = userService.existByEmail(socialLoginEmailVerify);
        return ResponseEntity.ok(userExistDto);
    }

    @GetMapping
    public ResponseEntity<UserFindAllResponse> findJoinedAllUser(@RequestParam("id") Long id,
                                            Pageable pageable) {
        Page<UserBasicDto> joinedUser = userService.findJoinedAllUser(id, pageable);

        UserFindAllResponse response = new UserFindAllResponse(
                joinedUser.getContent(),
                joinedUser.hasNext(),
                joinedUser.hasPrevious(),
                joinedUser.getNumber()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<UserDetailsDto> modifyUser(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsDto) {
        UserDetailsDto modifiedUser = userService.modifyUser(id, userDetailsDto);
        return ResponseEntity.ok(modifiedUser);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        Map<String, String> response = new HashMap<>();
        response.put("result", "OK");

        return ResponseEntity.ok(response);
    }
}
