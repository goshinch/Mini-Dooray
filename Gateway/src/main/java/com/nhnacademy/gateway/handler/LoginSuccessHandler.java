package com.nhnacademy.gateway.handler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final RedisTemplate<String, Object> redisTemplate;

    public LoginSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            String login = user.getUsername();

            HttpSession session = request.getSession();
            session.setAttribute("id", login);

            Cookie cookie = new Cookie("LOGIN", session.getId());
            response.addCookie(cookie);

            redisTemplate.opsForHash().put(session.getId(), "id", login);

            response.sendRedirect("/main");
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
        }
    }
}
