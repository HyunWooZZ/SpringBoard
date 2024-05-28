package com.example.firstWebPage.entity

import jakarta.persistence.*

@Entity
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(length = 200)
        val title: String,

        @Column(columnDefinition = "TEXT")
        val content: String,

        // 하나의 유저는 여러개의 개시글을 갖을 수 있음
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: AppUser
)