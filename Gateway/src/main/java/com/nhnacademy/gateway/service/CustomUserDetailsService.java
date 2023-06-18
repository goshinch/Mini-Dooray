package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.config.AccountServerProperties;
import com.nhnacademy.gateway.domain.Role;
import com.nhnacademy.gateway.domain.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountServerProperties accountServerProperties;
    private final RestTemplate restTemplate;
    private final String userInfo;

    public CustomUserDetailsService(AccountServerProperties accountServerProperties, RestTemplate restTemplate, @Value("${account.api.url.user-info}") String userInfo) {
        this.accountServerProperties = accountServerProperties;
        this.restTemplate = restTemplate;
        this.userInfo = userInfo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 일반 회원가입 로직을 구현
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserInfoResponse> existEntity = new HttpEntity<>(headers);
        ResponseEntity<UserInfoResponse> responseUserExist = restTemplate.exchange(
                accountServerProperties.accountServerProvider() + userInfo + "/" + username,
                HttpMethod.GET,
                existEntity,
                UserInfoResponse.class
        );
        return buildUserDetails(Objects.requireNonNull(responseUserExist.getBody()));
    }

    private UserDetails buildUserDetails(UserInfoResponse userResponse) {
        String userId = userResponse.getUserId();
        String encodedPassword = userResponse.getPassword();
        Collection<? extends GrantedAuthority> authorities = getAuthorities(Collections.singleton(userResponse.getRole()));

        return new User(userId, encodedPassword, authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}
