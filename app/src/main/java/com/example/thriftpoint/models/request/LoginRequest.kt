package com.example.thriftpoint.models.request

data class LoginRequest(
    val email: String,
    val password: String
)