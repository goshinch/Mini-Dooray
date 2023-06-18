package com.nhnacademy.gateway.handler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO OAuth2LoginSuccessHandler 빈 클래스로 oAuth2 인증 성공시 실행되는 핸들러
 *
 * SimpleUrlAuthenticationSuccessHandler 는 spring security 의 핸들러 클래스로
 * 1. 사용자가 인증에 성공했을 시 설정된 기본 URL 로 리다이렉트 한다.
 * 2. 필요에 따라 인증 성공 시 사용자를 다른 URL 로 리다이렉트할 수 있도록 설정할 수 있다.
 * 3. HttpServletResponse 객체를 사용하여 사용자에게 추가적인 응답 정보를 전달할 수 있다.
 * */
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RedisTemplate<String, Object> redisTemplate;

    public OAuth2LoginSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /**
         * Authentication 객체에서 인증된 사용자의 세부 정보를 가져온다.
         * */
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String login = oAuth2User.getAttribute("id");
        String loginEmail = oAuth2User.getAttribute("email");
        String loginName = oAuth2User.getAttribute("name");
        String authority = new ArrayList<>(oAuth2User.getAuthorities()).get(0).getAuthority();
        /**
         * 세션 요청에 인증된 사용자의 세부 정보를 감싸는 로직
         * */
        HttpSession session = request.getSession();
        session.setAttribute("id", login);
        session.setAttribute("email", loginEmail);
        session.setAttribute("name", loginName);

        Cookie cookie = new Cookie("LOGIN", session.getId());
        response.addCookie(cookie);

        redisTemplate.opsForHash().put(session.getId(), "id", login);
        redisTemplate.opsForHash().put(session.getId(),"email", loginEmail);
        redisTemplate.opsForHash().put(session.getId(),"name", loginName);

        /**
         * 리다이렉트 URL 처리
         * */
        getRedirectStrategy().sendRedirect(request, response, "/main");
    }

    private String determineTargetUrl(Authentication authentication) {
        // 인증된 사용자의 권한에 따라 리다이렉트할 URL 을 결정
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return "/admin";
        } else {
            return "/user";
        }
    }
}

