package com.preetanshumishra.FinBossAndroid.data.models

data class ApiResponse<T>(
    val status: String,
    val message: String? = null,
    val data: T? = null
)
