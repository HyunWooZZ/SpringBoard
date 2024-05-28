package com.example.firstWebPage.repository

import com.example.firstWebPage.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>