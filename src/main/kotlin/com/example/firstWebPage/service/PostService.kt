package com.example.firstWebPage.service

import com.example.firstWebPage.entity.Post
import com.example.firstWebPage.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PostService @Autowired constructor(
        private val postRepository: PostRepository
) {

    fun findAll(): List<Post> {
        return postRepository.findAll().ifEmpty { listOf() }
    }

    fun findById(id: Long): Optional<Post> {
        return postRepository.findById(id)
    }

    fun save(post: Post): Post {
        return postRepository.save(post)
    }
}