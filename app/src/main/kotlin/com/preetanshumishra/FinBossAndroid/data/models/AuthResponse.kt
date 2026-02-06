package com.preetanshumishra.FinBossAndroid.data.models

data class AuthResponse(
    val userId: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val accessToken: String,
    val refreshToken: String
)

fun AuthResponse.toUser() = User(
    id = userId,
    email = email,
    firstName = firstName,
    lastName = lastName
)
