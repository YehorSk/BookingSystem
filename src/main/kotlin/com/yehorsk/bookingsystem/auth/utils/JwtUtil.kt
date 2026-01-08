package com.yehorsk.bookingsystem.auth.utils

import com.yehorsk.bookingsystem.auth.exceptions.types.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date


@Component
class JwtUtil(
    @param:Value($$"${secret}")
    private val SECRET: String
) {
    private val signingKey = Keys.hmacShaKeyFor(
        Decoders.BASE64.decode(SECRET)
    )
    private val accessTokenValidityMs = 15L * 60L * 1000L

    fun generateToken(userId: String): String{
        val now = Date()
        return Jwts.builder()
            .subject(userId)
            .issuedAt(now)
            .claim("type","access")
            .expiration(Date(now.time + accessTokenValidityMs))
            .signWith(signingKey, Jwts.SIG.HS256)
            .compact()
    }

    fun extractUserId(token: String): String {
        val claims = getClaims(token) ?: throw InvalidTokenException()
        return claims.subject
    }

    fun validateAccessToken(token: String): Boolean {
        val claims = getClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "access"
    }

    fun isTokenExpired(token: String): Boolean {
        val claims = getClaims(token) ?: return false
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