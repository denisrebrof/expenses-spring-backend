package com.upreality.expensesspringbackend.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
@Component
class JWTUtil {

    fun generateToken(userDetails: UserDetails): String? {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userDetails.username, EXPIRATION_OFFSET)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean? {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun extractUsername(token: String?): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String?): Date {
        return extractClaim<Date>(token, Claims::getExpiration)
    }

    fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun createToken(
        claims: Map<String, Any>,
        subject: String,
        expirationOffset: Long,
        currentTime: Long = System.currentTimeMillis()
    ): String? {
        val issuedAt = Date(currentTime)
        val expiration = Date(currentTime + expirationOffset)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    companion object {
        private const val SECRET_KEY = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret"
        private const val EXPIRATION_OFFSET: Long = 1000 * 60 * 60 * 10
    }
}