package com.example.firstWebPage

import com.example.firstWebPage.entity.AppUser
import com.example.firstWebPage.service.AppUserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FirstWebPageApplication {
	// @Bean
//	fun init(appUserService: AppUserService) = CommandLineRunner {
//		appUserService.save(AppUser( username = "user", password = "user"))
//		appUserService.save(AppUser( username = "admin", password = "admin"))
//	}
}

fun main(args: Array<String>) {
	runApplication<FirstWebPageApplication>(*args)
}
