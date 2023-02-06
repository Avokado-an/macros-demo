package com.bahdanau.aim.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BearerAuthFeignConfig {
    //public static final String CLIENT_REGISTRATION_ID = "keycloak";
//
    //private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    //private final ClientRegistrationRepository clientRegistrationRepository;
//
    //public BearerAuthFeignConfig(OAuth2AuthorizedClientService oAuth2AuthorizedClientService,
    //                        ClientRegistrationRepository clientRegistrationRepository) {
    //    this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    //    this.clientRegistrationRepository = clientRegistrationRepository;
    //}
//
    //@Bean
    //public RequestInterceptor requestInterceptor() {
    //    ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID);
    //    OAuthClientCredentialsFeignManager clientCredentialsFeignManager =
    //            new OAuthClientCredentialsFeignManager(authorizedClientManager(), clientRegistration);
    //    return requestTemplate -> {
    //        requestTemplate.header("Authorization", "Bearer " + clientCredentialsFeignManager.getAccessToken());
    //    };
    //}
}
