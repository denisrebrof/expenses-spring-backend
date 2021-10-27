package com.upreality.expensesspringbackend.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames

@Configuration
class OAuthConfiguration {

    private var googleClientId = "405077401282-a1hkiv1k470atvih8rha869nue8pl9ns.apps.googleusercontent.com"

    private var googleSecret: String = System.getenv("GOOGLE_SIGN_IN_SECRET")

    private val redirectUri = "http://localhost:8000/authorized"

    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository? {
        val registration = googleClientRegistration()
        return InMemoryClientRegistrationRepository(registration)
    }

    private fun googleClientRegistration(): ClientRegistration? {
        return ClientRegistration
            .withRegistrationId("google")
            .clientId(googleClientId)
            .clientSecret(googleSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUriTemplate(redirectUri)
            .scope("openid", "profile", "email", "phone")
            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .clientName("Google")
            .build()
    }
}