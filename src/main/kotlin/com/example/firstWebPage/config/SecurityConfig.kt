package com.example.firstWebPage.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val accountService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
){
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize ->
                authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/create_user").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
            }
                .formLogin { form ->
                    form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/board", true) // 로그인 성공 후 리다이렉트 설정
                        .failureUrl("/login?error=true") // 로그인 실패 시 리다이렉트 설정
                        .permitAll()
                }
                .logout { logout -> logout.permitAll() }
                .csrf {  csrf -> csrf.ignoringRequestMatchers("/h2-console/**") }// H2 콘솔 접근 시 CSRF 비활성화
                .headers { headers -> headers.frameOptions { frameOptions -> frameOptions.sameOrigin() } }

        return http.build()
    }
}