package com.nhnacademy.minidoorayuserapi.user.repository;

import com.nhnacademy.minidoorayuserapi.user.dto.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepositoryCustom {
    List<UserDto> findUserAllBy();

    Optional<UserDto> findUserDetailByUserEmail(String email);

    Page<UserBasicDto> findJoinedAllUserByPage(Long currentUserNo, Pageable pageable);

    UserExistDto existByEmail(String email);
}
