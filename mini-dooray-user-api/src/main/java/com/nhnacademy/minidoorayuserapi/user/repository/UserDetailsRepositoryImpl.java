package com.nhnacademy.minidoorayuserapi.user.repository;

import com.nhnacademy.minidoorayuserapi.user.dto.UserDetailsDto;
import com.nhnacademy.minidoorayuserapi.user.dto.UserPasswordDto;
import com.nhnacademy.minidoorayuserapi.user.entity.QUser;
import com.nhnacademy.minidoorayuserapi.user.entity.QUserDetails;
import com.nhnacademy.minidoorayuserapi.user.entity.UserDetails;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class UserDetailsRepositoryImpl extends QuerydslRepositorySupport implements UserDetailsRepositoryCustom {
    public UserDetailsRepositoryImpl() {
        super(UserDetails.class);
    }

    @Override
    public Optional<UserPasswordDto> findPasswordByUserId(String userId) {
        QUserDetails userDetails = QUserDetails.userDetails;

        return Optional.ofNullable(from(userDetails)
            .where(userDetails.userId.eq(userId))
            .select(Projections.bean(UserPasswordDto.class, userDetails.password))
            .fetchOne()
        );
    }

    @Override
    public Optional<UserDetailsDto> findUserDetailsById(String userId) {

        QUserDetails userDetails = QUserDetails.userDetails;
        QUser user = QUser.user;

        UserDetailsDto userSampleDto = from(userDetails)
                .leftJoin(user).on(user.id.eq(userDetails.user.id)).fetchJoin()
                .where(userDetails.userId.eq(userId))
                .select(Projections.bean(UserDetailsDto.class,
                        userDetails.id,
                        userDetails.userId,
                        userDetails.password,
                        user.name,
                        user.email,
                        user.status,
                        user.role
                ))
                .fetchOne();

        return Optional.ofNullable(userSampleDto);
    }
}
