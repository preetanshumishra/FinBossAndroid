package com.preetanshumishra.FinBossAndroid.utils

import com.preetanshumishra.FinBossAndroid.data.models.AuthResponse
import com.preetanshumishra.FinBossAndroid.data.models.User

fun AuthResponse.toUser() = User(
    id = userId,
    email = email,
    firstName = firstName,
    lastName = lastName
)
