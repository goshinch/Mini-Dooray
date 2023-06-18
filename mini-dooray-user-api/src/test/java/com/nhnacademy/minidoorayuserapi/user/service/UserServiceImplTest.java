//package com.nhnacademy.minidoorayuserapi.user.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//import com.nhnacademy.minidoorayuserapi.user.dto.SocialLoginEmailVerifyDto;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserBasicDto;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserDetailsDto;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserPasswordDto;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserSignUpRequest;
//import com.nhnacademy.minidoorayuserapi.user.entity.User;
//import com.nhnacademy.minidoorayuserapi.user.entity.UserType;
//import com.nhnacademy.minidoorayuserapi.user.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import com.nhnacademy.minidoorayuserapi.user.repository.UserTypeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//
//@ExtendWith(MockitoExtension.class)
//@Transactional
//class UserServiceImplTest {
//    @InjectMocks
//    UserServiceImpl userServiceImpl;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    UserTypeRepository userTypeRepository;
//
//    @Test
//    void findUserPassword() {
//        UserPasswordDto passwordDto = new UserPasswordDto();
//        passwordDto.setPassword("password");
//        given(userRepository.findPasswordByUserId("id"))
//            .willReturn(Optional.of(passwordDto));
//
//        assertThat(userServiceImpl.findUserPassword("id").getPassword())
//            .isEqualTo(passwordDto.getPassword());
//    }
//
//    @Test
//    void findUserDetailsByUserId() {
//        UserDetailsDto userDetailsDto = new UserDetailsDto();
//        userDetailsDto.setId(1L);
//        userDetailsDto.setUserId("id");
//        userDetailsDto.setPassword("password");
//        userDetailsDto.setEmail("email@email.nhn");
//        userDetailsDto.setStatus(User.UserStatus.DORMANT_STATE);
//
//        given(userRepository.findUserDetailsById("id"))
//            .willReturn(Optional.of(userDetailsDto));
//
//        UserDetailsDto searched = userServiceImpl.findUserDetailsByUserId("id");
//        assertThat(searched.getUserId()).isEqualTo("id");
//        assertThat(searched.getPassword()).isEqualTo("password");
//        assertThat(searched.getStatus()).isEqualTo(User.UserStatus.DORMANT_STATE);
//    }
//
//    @Test
//    void signUp() {
//        String id = "id";
//        String pw = "pw123";
//        String email = "id@abc.com";
//        String name = "abc";
//        UserType.Type type = UserType.Type.DEFAULT;
//
//        UserSignUpRequest body = new UserSignUpRequest();
//        body.setUserId(id);
//        body.setPassword(pw);
//        body.setEmail(email);
//        body.setName(name);
//        body.setType(type);
//
//        UserType userType = new UserType();
//
//        given(userTypeRepository.save(any()))
//                .willReturn(userType);
//
//        UserSignUpRequest saved = userServiceImpl.signUp(body);
//        assertThat(saved).isEqualTo(body);
//    }
//
//    @Test
//    void findUserDetailsByUserEmailTest() {
//        String email = "user1@nhnacademy.com";
//        SocialLoginEmailVerifyDto emailVerifyDto = new SocialLoginEmailVerifyDto();
//        emailVerifyDto.setEmail(email);
//
//        UserDetailsDto userDetailsDto = new UserDetailsDto();
//        userDetailsDto.setId(1L);
//        userDetailsDto.setUserId("id");
//        userDetailsDto.setPassword("password");
//        userDetailsDto.setEmail("email@email.nhn");
//        userDetailsDto.setStatus(User.UserStatus.DORMANT_STATE);
//
//        given(userRepository.findUserDetailByUserEmail(emailVerifyDto.getEmail()))
//            .willReturn(Optional.of(userDetailsDto));
//
//        UserDetailsDto result = userServiceImpl.findByUserDetailsByEmail(emailVerifyDto);
//
//        assertThat(result).isEqualTo(userDetailsDto);
//    }
//
//    @Test
//    void findJoinedAllUserTest() {
//        Pageable pageable = PageRequest.of(0, 5);
//        given(userRepository.findJoinedAllUserByPage(100L, pageable))
//            .willReturn(new PageImpl<>(new ArrayList<>(),pageable,0));
//
//        Page<UserBasicDto> joinedUser = userRepository.findJoinedAllUserByPage(100L, pageable);
//        assertThat(joinedUser.hasContent()).isFalse();
//        assertThat(joinedUser.hasNext()).isFalse();
//        assertThat(joinedUser.hasPrevious()).isFalse();
//    }
//}
