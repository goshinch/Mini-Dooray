//package com.nhnacademy.minidoorayuserapi.user.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.nhnacademy.minidoorayuserapi.user.dto.UserBasicDto;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserDetailsDto;
//import com.nhnacademy.minidoorayuserapi.user.dto.UserPasswordDto;
//import com.nhnacademy.minidoorayuserapi.user.entity.User;
//import java.time.LocalDateTime;
//import javax.persistence.EntityManager;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class UserRepositoryTest {
//    @Autowired
//    EntityManager entityManager;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    void userSignUpTest() {
//        User user = User.builder()
//                .userId("id")
//                .name("test")
//                .password("pw123")
//                .email("id@abc.com")
//                .status(User.UserStatus.ACTIVITY)
//                .createdAt(LocalDateTime.now())
//                .build();
//        entityManager.persist(user);
//
////        userRepository.saveAndFlush(user);
//
//        userRepository.findById(user.getId())
//            .get()
//            .equals(user);
//    }
//
//    @Test
//    void findPasswordByUserIdTest() {
//        User user = User.builder()
//                .userId("id")
//                .name("test")
//                .password("pw123")
//                .email("id@abc.com")
//                .status(User.UserStatus.ACTIVITY)
//                .createdAt(LocalDateTime.now())
//                .build();
//        entityManager.persist(user);
//        UserPasswordDto userPasswordDto = userRepository.findPasswordByUserId("id").get();
//        assertThat(userPasswordDto.getPassword()).isEqualTo("pw123");
//    }
//
//    @Test
//    void findUserDetailsByIdTest() {
//        User user = User.builder()
//                .userId("id")
//                .name("test")
//                .password("pw123")
//                .email("id@abc.com")
//                .status(User.UserStatus.ACTIVITY)
//                .createdAt(LocalDateTime.now())
//                .build();
//        entityManager.persist(user);
//        UserDetailsDto userDetailsDto = userRepository.findUserDetailsById("id").get();
//        assertThat(userDetailsDto.getUserId()).isEqualTo("id");
//        assertThat(userDetailsDto.getPassword()).isEqualTo("pw123");
//        assertThat(userDetailsDto.getStatus()).isEqualTo(User.UserStatus.ACTIVITY);
//    }
//
//    @Test
//    void findUserDetailsByEmail() {
//        UserDetailsDto userDetailsDto = userRepository.findUserDetailByUserEmail("user1@nhnacademy.com")
//            .get();
//        assertThat(userDetailsDto.getUserId()).isEqualTo("user1");
//        assertThat(userDetailsDto.getEmail()).isEqualTo("user1@nhnacademy.com");
//    }
//
//    @Test
//    void findAllJoinedUsers() {
//        Pageable pageable = PageRequest.of(0, 6);
//        Page<UserBasicDto> joinedUsers = userRepository.findJoinedAllUserByPage(100L, pageable);
//
//        assertThat(joinedUsers.getContent()).hasSize(7);
//        assertThat(joinedUsers.hasNext()).isTrue();
//    }
//}
//
