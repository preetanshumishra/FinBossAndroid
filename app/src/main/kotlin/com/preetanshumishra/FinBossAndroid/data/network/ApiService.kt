package com.preetanshumishra.FinBossAndroid.data.network

import com.preetanshumishra.FinBossAndroid.data.models.ApiResponse
import com.preetanshumishra.FinBossAndroid.data.models.AuthResponse
import com.preetanshumishra.FinBossAndroid.data.models.LoginRequest
import com.preetanshumishra.FinBossAndroid.data.models.RegisterRequest
import com.preetanshumishra.FinBossAndroid.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<AuthResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<AuthResponse>

    @GET("/api/v1/auth/profile")
    suspend fun getProfile(): ApiResponse<User>

    @POST("/api/v1/auth/refresh")
    suspend fun refreshToken(@Body refreshToken: Map<String, String>): ApiResponse<Map<String, String>>
}
