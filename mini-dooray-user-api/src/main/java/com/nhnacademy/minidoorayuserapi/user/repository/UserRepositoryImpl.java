package com.nhnacademy.minidoorayuserapi.user.repository;

import com.nhnacademy.minidoorayuserapi.user.dto.*;
import com.nhnacademy.minidoorayuserapi.user.entity.QUser;
import com.nhnacademy.minidoorayuserapi.user.entity.User;
import com.querydsl.core.types.Projections;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<UserDto> findUserAllBy() {
        QUser user = QUser.user;
        return from(user)
                .select(Projections.bean(
                        UserDto.class,
                        user.id,
                        user.name,
                        user.email,
                        user.status
                        )
                )
                .fetch();
    }

    @Override
    public Optional<UserDto> findUserDetailByUserEmail(String email) {
        QUser user = QUser.user;

        UserDto userDto = from(user)
            .where(user.email.eq(email))
            .select(Projections.bean(UserDto.class,
                user.id,
                user.name,
                user.email,
                user.status))
            .fetchOne();

        return Optional.ofNullable(userDto);
    }

    @Override
    public Page<UserBasicDto> findJoinedAllUserByPage(Long currentUserNo, Pageable pageable) {
        QUser user = QUser.user;
        List<UserBasicDto> joinedUsers = from(user)
            .where(user.id.notIn(currentUserNo).and(user.status.eq(User.UserStatus.ACTIVITY)))
            .select(
                Projections.bean(
                    UserBasicDto.class,
                    user.id,
                    user.name,
                    user.email
                )
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return PageableExecutionUtils.getPage(joinedUsers, pageable, joinedUsers::size);
    }

    @Override
    public UserExistDto existByEmail(String email) {
        UserExistDto userExistDto = new UserExistDto();
        QUser user = QUser.user;
        Long count = from(user)
                .where(user.email.eq(email))
                .fetchCount();

        boolean exist = count > 0;
        userExistDto.setExist(exist);
        return userExistDto;
    }
}
