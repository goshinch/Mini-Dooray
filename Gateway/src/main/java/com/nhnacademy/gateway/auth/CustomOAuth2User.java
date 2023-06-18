package com.nhnacademy.gateway.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * TODO 애플리케이션에서 OAuth2 사용자 서비스 동작을 커스터 마이징한 구현체
 *
 * OAuth2User 인터페이스는 spring security OAuth2 모듈에서 제공하는 인터페이스
 * OAuth2 인증을 통해 인증된 사용자 속성 정보와 권한 정보를 제공하는 메서드를 정의한다.
 * getAttributes() : OAuth2 인증을 통해 인증된 사용자의 속성 정보를 Map 현태로 반환
 * getAuthorities() : OAuth2 인증을 통해 인증된 사용자의 권한 정보를 반환
 * getName() : OAuth2 인증을 통해 인증된 사용자의 이름을 반환
 * */
public class CustomOAuth2User implements OAuth2User {
    /**
     * 실제 사용자 정보를 가져오는 클래스
     * */
    private OAuth2User user;

    public CustomOAuth2User(OAuth2User user) {
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return user.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getName() {
        return user.getAttribute("name");
    }

    public String getEmail() {
        return user.getAttribute("email");
    }

    public String getLogin() {
        return user.getAttribute("login");
    }
}
