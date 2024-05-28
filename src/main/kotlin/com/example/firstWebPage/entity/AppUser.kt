package com.example.firstWebPage.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class AppUser(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @Column(length = 200)
        val username: String,
        @Column(length = 400)
        val password: String,
        @Column(length = 200)
        val role: String
)