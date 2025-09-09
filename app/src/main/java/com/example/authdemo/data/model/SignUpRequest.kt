package com.example.authdemo.data.model

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String,
    val role: String = "user"
)
