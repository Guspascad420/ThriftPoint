package com.example.thriftpoint.models.request

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String
)