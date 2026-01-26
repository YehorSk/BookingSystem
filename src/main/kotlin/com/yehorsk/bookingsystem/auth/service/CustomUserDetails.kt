package com.yehorsk.bookingsystem.auth.service

import com.yehorsk.bookingsystem.auth.database.entity.UserEntity
import com.yehorsk.bookingsystem.common.type.UserId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val user: UserEntity
) : UserDetails{

    override fun getAuthorities(): List<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
    }

    val id: UserId get() = user.id!!

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.email

    override fun isCredentialsNonExpired() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isEnabled() = user.enabled

}