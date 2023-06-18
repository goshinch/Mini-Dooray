package com.nhnacademy.minidoorayuserapi.user.service;

import com.nhnacademy.minidoorayuserapi.user.dto.*;
import com.nhnacademy.minidoorayuserapi.user.entity.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<UserDto> findUserDetails();
    UserPasswordDto findUserPassword (String userId);
    UserDetailsDto findUserDetailsByUserId (String userId);
    UserSignUpRequest signUp(UserSignUpRequest signUpRequest);
    UserGitSignUpRequest gitSignUp(UserGitSignUpRequest gitSignUpRequest);
    UserDto findByUserDetailsByEmail(SocialLoginEmailVerifyDto socialLoginEmailVerify);
    UserExistDto existByEmail(String socialLoginEmailVerify);
    Page<UserBasicDto> findJoinedAllUser(Long userNo, Pageable pageable);
    UserDetailsDto modifyUser(Long id, UserDetailsDto userDetailsDto);
    void deleteUser(Long id);
}
