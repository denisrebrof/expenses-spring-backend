package com.upreality.expensesspringbackend.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val myUserDetailService: UserDetailsServiceImpl? = null

    @Autowired
    private val jwtRequestFilter: JWTRequestFilter? = null

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        super.configure(auth)
        auth.userDetailsService<UserDetailsService?>(myUserDetailService)
    }

    @Throws(Exception::class)
    override fun configure(security: HttpSecurity) {
        security.csrf().disable()
            .authorizeRequests()
            .antMatchers("/authenticate").permitAll()
            .antMatchers("/hello2").permitAll()
            .anyRequest().authenticated().and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().oauth2Login()
        security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

//    @Bean
//    fun clientRegistrationRepository(): ClientRegistrationRepository? {
//        return InMemoryClientRegistrationRepository(googleClientRegistration())
//    }

//    private open fun googleClientRegistration(): ClientRegistration? {
//        return ClientRegistration.withRegistrationId("google")
//            .clientId("google-client-id")
//            .clientSecret("google-client-secret")
//            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//            .scope("openid", "profile", "email", "address", "phone")
//            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//            .userNameAttributeName(IdTokenClaimNames.SUB)
//            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//            .clientName("Google")
//            .build()
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManager(): AuthenticationManager? {
        return super.authenticationManager()
    }

}