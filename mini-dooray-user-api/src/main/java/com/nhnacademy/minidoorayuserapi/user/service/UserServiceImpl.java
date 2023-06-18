package com.nhnacademy.minidoorayuserapi.user.service;

import com.nhnacademy.minidoorayuserapi.user.dto.*;
import com.nhnacademy.minidoorayuserapi.user.entity.User;
import com.nhnacademy.minidoorayuserapi.exceptions.UserNotFoundException;
import com.nhnacademy.minidoorayuserapi.user.entity.UserDetails;
import com.nhnacademy.minidoorayuserapi.user.entity.UserType;
import com.nhnacademy.minidoorayuserapi.user.repository.UserDetailsRepository;
import com.nhnacademy.minidoorayuserapi.user.repository.UserRepository;

import java.util.List;

import com.nhnacademy.minidoorayuserapi.user.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public List<UserDto> findUserDetails() {
        return userRepository.findUserAllBy();
    }

    @Override
    public UserPasswordDto findUserPassword (String userId){
        return userDetailsRepository.findPasswordByUserId(userId)
            .orElse(null);
    }

    @Override
    public UserDetailsDto findUserDetailsByUserId (String userId){
        return userDetailsRepository.findUserDetailsById(userId)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾지 못하였습니다."));
    }

    @Override
    public UserSignUpRequest signUp(UserSignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setStatus(User.UserStatus.ACTIVITY);
        user.setRole(User.Role.ROLE_USER);

        User newUser = userRepository.save(user);

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(signUpRequest.getUserId());
        userDetails.setPassword(signUpRequest.getPassword());
        userDetails.setUser(newUser);

        userDetailsRepository.save(userDetails);

        UserType.Pk pk = new UserType.Pk();
        pk.setUserId(newUser.getId());

        UserType userType = new UserType();
        userType.setUser(newUser);
        userType.setPk(pk);
        userType.setType(UserType.Type.DEFAULT);

        userTypeRepository.save(userType);

        return signUpRequest;
    }

    @Override
    public UserGitSignUpRequest gitSignUp(UserGitSignUpRequest gitSignUpRequest) {
        User user = new User();
        user.setName(gitSignUpRequest.getName());
        user.setEmail(gitSignUpRequest.getEmail());
        user.setStatus(User.UserStatus.ACTIVITY);
        user.setRole(User.Role.ROLE_USER);

        User newUser = userRepository.save(user);

        UserType.Pk pk = new UserType.Pk();
        pk.setUserId(newUser.getId());

        UserType userType = new UserType();
        userType.setUser(newUser);
        userType.setPk(pk);
        userType.setType(UserType.Type.GIT);

        userTypeRepository.save(userType);

        return gitSignUpRequest;
    }

    @Override
    public UserDto findByUserDetailsByEmail(SocialLoginEmailVerifyDto socialLoginEmailVerify) {
        return userRepository.findUserDetailByUserEmail(socialLoginEmailVerify.getEmail())
            .orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾지 못하였습니다."));
    }

    @Override
    public UserExistDto existByEmail(String socialLoginEmailVerify) {
        return userRepository.existByEmail(socialLoginEmailVerify);
    }

    @Override
    public Page<UserBasicDto> findJoinedAllUser(Long userNo, Pageable pageable) {
        return userRepository.findJoinedAllUserByPage(userNo, pageable);
    }

    @Override
    public UserDetailsDto modifyUser(Long id, UserDetailsDto userDetailsDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾지 못하였습니다."));
        UserDetails userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾지 못하였습니다."));

        user.setName(userDetailsDto.getName());
        user.setEmail(userDetailsDto.getEmail());
        user.setStatus(userDetailsDto.getStatus());
        userDetails.setUserId(userDetailsDto.getUserId());
        userDetails.setPassword(userDetailsDto.getPassword());
        userDetails.setUser(user);

        User modifiedUser = userRepository.save(user);
        UserDetails modifiedUserDetails = userDetailsRepository.save(userDetails);

        UserDetailsDto modifiedUserDetailsDto = new UserDetailsDto();
        modifiedUserDetailsDto.setId(modifiedUser.getId());
        modifiedUserDetailsDto.setUserId(modifiedUserDetails.getUserId());
        modifiedUserDetailsDto.setName(modifiedUser.getName());
        modifiedUserDetailsDto.setPassword(modifiedUserDetails.getPassword());
        modifiedUserDetailsDto.setEmail(modifiedUser.getEmail());
        modifiedUserDetailsDto.setStatus(modifiedUser.getStatus());

        return modifiedUserDetailsDto;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
