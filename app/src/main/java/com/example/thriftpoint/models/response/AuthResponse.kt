package com.example.thriftpoint.models.response

data class AuthResponse(
    val meta: MetaResponse,
    val data: AuthDataResponse
)

data class AuthDataResponse(
    val token: String
)