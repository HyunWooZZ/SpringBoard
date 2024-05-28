package com.example.firstWebPage.controller

import com.example.firstWebPage.entity.AppUser
import com.example.firstWebPage.entity.Post
import com.example.firstWebPage.service.PostService
import com.example.firstWebPage.service.AppUserService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BoardController(
        private val postService: PostService,
        private val appUserService: AppUserService
) {
    @GetMapping("/board")
    fun board(model: Model): String {
        model.addAttribute("posts", postService.findAll())
        return "board"
    }
    @GetMapping("/post/{id}")
    fun viewPost(@PathVariable id: Long, model: Model): String {
        val post = postService.findById(id).orElseThrow { IllegalArgumentException("Invalid post Id:$id") }
        model.addAttribute("post", post)
        return "post"
    }
    @PostMapping("/post")
    fun createPost(
            @AuthenticationPrincipal userDetails: User,
            @RequestParam title: String,
            @RequestParam content: String
    ): String {
        val user: AppUser = appUserService.findByUserName(userDetails.username) ?: throw IllegalArgumentException("Invalid User")
        val post = Post(title=title, content=content, user=user)
        postService.save(post)
        return "redirect:/board"
    }
}