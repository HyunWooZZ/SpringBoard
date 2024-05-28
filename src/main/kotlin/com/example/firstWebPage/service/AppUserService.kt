package com.example.firstWebPage.service

import com.example.firstWebPage.entity.AppUser
import com.example.firstWebPage.repository.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AppUserService @Autowired constructor(
        private val appUserRepository: AppUserRepository,
        private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    fun findByUserName(userName: String): AppUser? {
        return appUserRepository.findByUsername(userName)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val appUser = appUserRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found: $username")
        return User.builder()
            .username(appUser.username)
            .password(appUser.password) // 패스워드는 이미 인코딩된 상태로 저장됨
            .roles(appUser.role)
            .build()
    }

    fun save(username: String, password: String, role: String): AppUser {
        val encodedPassword: String = this.passwordEncoder.encode(password)
        return appUserRepository.save(AppUser(username = username, password = encodedPassword, role = role))
    }
}