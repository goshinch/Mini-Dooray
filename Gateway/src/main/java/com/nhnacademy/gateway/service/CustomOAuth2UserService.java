package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.config.AccountServerProperties;
import com.nhnacademy.gateway.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final RestTemplate restTemplate;
    private final String signUp;
    private final String emailExist;
    private final AccountServerProperties accountServerProperties;

    public CustomOAuth2UserService(RestTemplate restTemplate, @Value("${account.api.git.url.signup}") String signUp, @Value("${account.api.url.email-exist}") String emailExist, AccountServerProperties accountServerProperties) {
        this.restTemplate = restTemplate;
        this.signUp = signUp;
        this.emailExist = emailExist;
        this.accountServerProperties = accountServerProperties;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String userInfoEndpointUri = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            HttpHeaders headers = getGithubHttpHeaders();
            headers.setBearerAuth(userRequest.getAccessToken().getTokenValue());
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<UserInfoResponse> response = restTemplate.exchange(
                    userInfoEndpointUri,
                    HttpMethod.GET,
                    entity,
                    UserInfoResponse.class);

            Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

            if (response.getStatusCode() == HttpStatus.OK) {
                UserInfoResponse userInfo = response.getBody();
                attributes.put("name", userInfo.getName());
                attributes.put("login", userInfo.getLogin());

            } else throw new OAuth2AuthenticationException(new OAuth2Error("userinfo_endpoint_error"), "Error accessing the userinfo endpoint: " + response.getStatusCode());

            ResponseEntity<UserOAuthEmailResponse[]> responseEmail = restTemplate.exchange(
                    userInfoEndpointUri + "/emails",
                    HttpMethod.GET,
                    entity,
                    UserOAuthEmailResponse[].class);

            if (responseEmail.getStatusCode() == HttpStatus.OK) {
                UserOAuthEmailResponse[] userInfo = responseEmail.getBody();
                String email = Arrays.stream(userInfo)
                        .sequential()
                        .filter(UserOAuthEmailResponse::getPrimary)
                        .findAny()
                        .get()
                        .getEmail();

                attributes.put("email", email);
                checkExistsUser(attributes.get("name"), attributes.get("email"));

                return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "email");
            } else throw new OAuth2AuthenticationException(new OAuth2Error("userinfo_endpoint_error"), "Error accessing the userinfo endpoint: " + response.getStatusCode());
        }
        return oAuth2User;
    }

    private void checkExistsUser(Object name, Object email) {
        GitSignForm gitSignForm = new GitSignForm();
        gitSignForm.setName(name.toString());
        gitSignForm.setEmail(email.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserExistDto> existEntity = new HttpEntity<>(headers);
        ResponseEntity<UserExistDto> responseUserExist = restTemplate.exchange(
                accountServerProperties.accountServerProvider() + emailExist + "?email=" + email,
                HttpMethod.GET,
                existEntity,
                UserExistDto.class
        );
        responseUserExist.getBody();
        if (!Objects.requireNonNull(responseUserExist.getBody()).isExist()) {
            HttpEntity<GitSignForm> signEntity = new HttpEntity<>(gitSignForm, headers);
            ResponseEntity<GitSignForm> responseSignUp = restTemplate.exchange(
                    accountServerProperties.accountServerProvider() + signUp,
                    HttpMethod.POST,
                    signEntity,
                    GitSignForm.class
            );
            responseSignUp.getBody();
        }
    }

    private HttpHeaders getGithubHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.parseMediaType("application/vnd.github+json")));
        headers.set("X-GitHub-Api-Version", "2022-11-28");
        return headers;
    }
}
