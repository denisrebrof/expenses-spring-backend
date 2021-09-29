package com.upreality.expensesspringbackend.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.safeCast

@Component
class JWTRequestFilter : OncePerRequestFilter() {

    @Autowired
    var jwtUtil: JWTUtil? = null

    @Autowired
    var userDetailsService: UserDetailsServiceImpl? = null

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authResult = request.getHeader("Authorization").let(this::getAuthentication)
        authResult.let(GetAuthenticationResult.Authenticated::class::safeCast)?.authentication?.apply {
            details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = this
        }
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(bearerToken: String): GetAuthenticationResult {
        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith("Bearer "))
            return GetAuthenticationResult.InvalidTokenFormat

        val jwtToken = bearerToken.substring(7, bearerToken.length)
        val username = jwtUtil?.extractUsername(jwtToken) ?: return GetAuthenticationResult.UsernameMissed

        if (SecurityContextHolder.getContext().authentication != null)
            return GetAuthenticationResult.AlreadyDefined

        val userDetails: UserDetails = userDetailsService!!.loadUserByUsername(username)

        if (jwtUtil!!.validateToken(jwtToken, userDetails) != true)
            return GetAuthenticationResult.InvalidToken

        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).let(
            GetAuthenticationResult::Authenticated
        )
    }

    private sealed class GetAuthenticationResult {
        object InvalidTokenFormat : GetAuthenticationResult()
        object UsernameMissed : GetAuthenticationResult()
        object AlreadyDefined : GetAuthenticationResult()
        object InvalidToken : GetAuthenticationResult()
        data class Authenticated(val authentication: UsernamePasswordAuthenticationToken) : GetAuthenticationResult()
    }
}