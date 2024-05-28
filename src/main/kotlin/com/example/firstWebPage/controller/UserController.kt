package com.example.firstWebPage.controller

import com.example.firstWebPage.entity.AppUser
import com.example.firstWebPage.service.AppUserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserController(
    private val appUserService: AppUserService
) {
    @GetMapping("/create_user")
    fun showCreateUserForm(model: Model): String {
        return "create_user"
    }

    @PostMapping("/create_user")
    fun createUser(
        @RequestParam("username") username: String,
        @RequestParam("password") password: String,
        @RequestParam("roles") role: String
    ): String {
        appUserService.save(username = username, password = password, role = role)
        return "redirect:/login"
    }
    //  로그인 페이지 매핑
    @GetMapping("/login")
    fun loginPage(@RequestParam(name = "error", required = false) error: String?, model: Model): String {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password")
        }
        return "login"
    }
}