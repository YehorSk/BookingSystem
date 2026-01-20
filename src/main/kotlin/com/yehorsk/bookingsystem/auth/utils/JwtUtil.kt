package com.yehorsk.bookingsystem.auth.utils

import com.yehorsk.bookingsystem.auth.exceptions.types.InvalidTokenException
import com.yehorsk.bookingsystem.auth.service.dto.responses.AccessTokens
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date


@Component
class JwtUtil(
    @param:Value("\${secret}")
    private val SECRET: String
) {
    private val signingKey = Keys.hmacShaKeyFor(
        Decoders.BASE64.decode(SECRET)
    )

    private val accessTokenValidityMs = 15L * 60L * 1000L
    private val refreshTokenValidityMs = 30L * 24L * 60L * 60L * 1000L

    fun generateToken(userId: String, role: String, type: String, lifespan: Long): String{
        val now = Date()
        return Jwts.builder()
            .subject(userId)
            .issuedAt(now)
            .claim("type",type)
            .claim("role", role)
            .expiration(Date(now.time + lifespan))
            .signWith(signingKey, Jwts.SIG.HS256)
            .compact()
    }

    fun generateTokens(userId: String, role: String): AccessTokens {
        return AccessTokens(
            generateAccessToken(userId, role),
            generateRefreshToken(userId, role)
        )
    }

    fun generateAccessToken(userId: String, role: String): String{
        return generateToken(
            userId = userId,
            type = "access",
            role = role,
            lifespan = accessTokenValidityMs
        )
    }

    fun generateRefreshToken(userId: String, role: String): String{
        return generateToken(
            userId = userId,
            type = "refresh",
            role = role,
            lifespan = refreshTokenValidityMs
        )
    }

    fun extractUserId(token: String): String {
        val claims = getClaims(token) ?: throw InvalidTokenException()
        return claims.subject
    }

    fun extractRole(token: String): String {
        val claims = getClaims(token) ?: throw InvalidTokenException()
        return claims["role"] as String
    }

    fun validateAccessToken(token: String): Boolean {
        val claims = getClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "access" && !isTokenExpired(token)
    }

    fun validateRefreshToken(token: String): Boolean {
        val claims = getClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "refresh" && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        val claims = getClaims(token) ?: return true
        return claims.expiration.before(Date())
    }

    private fun getClaims(token: String): Claims? {
        val rawToken = if(token.startsWith("Bearer ")){
            token.removePrefix("Bearer ")
        }else token

        return try{
            Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(rawToken)
                .payload
        }catch (e: Exception){
            null
        }
    }

}