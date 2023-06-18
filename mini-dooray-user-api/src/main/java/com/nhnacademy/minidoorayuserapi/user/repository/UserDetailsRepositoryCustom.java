package com.nhnacademy.minidoorayuserapi.user.repository;

import com.nhnacademy.minidoorayuserapi.user.dto.UserDetailsDto;
import com.nhnacademy.minidoorayuserapi.user.dto.UserPasswordDto;
import com.nhnacademy.minidoorayuserapi.user.dto.UserSampleDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserDetailsRepositoryCustom {
    Optional<UserPasswordDto> findPasswordByUserId(String userId);

    Optional<UserDetailsDto> findUserDetailsById(String userId);
}
