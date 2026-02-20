package com.preetanshumishra.FinBossAndroid.data.network

import com.preetanshumishra.FinBossAndroid.data.models.ApiResponse
import com.preetanshumishra.FinBossAndroid.data.models.AuthResponse
import com.preetanshumishra.FinBossAndroid.data.models.CreateTransactionRequest
import com.preetanshumishra.FinBossAndroid.data.models.LoginRequest
import com.preetanshumishra.FinBossAndroid.data.models.RegisterRequest
import com.preetanshumishra.FinBossAndroid.data.models.Transaction
import com.preetanshumishra.FinBossAndroid.data.models.UpdateTransactionRequest
import com.preetanshumishra.FinBossAndroid.data.models.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<AuthResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<AuthResponse>

    @GET("/api/v1/auth/profile")
    suspend fun getProfile(): ApiResponse<User>

    @POST("/api/v1/auth/refresh")
    suspend fun refreshToken(@Body refreshToken: Map<String, String>): ApiResponse<Map<String, String>>

    @POST("/api/v1/auth/logout")
    suspend fun logout(@Body body: Map<String, String>): ApiResponse<Unit?>

    @GET("/api/v1/transactions")
    suspend fun getTransactions(): ApiResponse<List<Transaction>>

    @POST("/api/v1/transactions")
    suspend fun createTransaction(@Body request: CreateTransactionRequest): ApiResponse<Transaction>

    @GET("/api/v1/transactions/{id}")
    suspend fun getTransaction(@Path("id") id: String): ApiResponse<Transaction>

    @PUT("/api/v1/transactions/{id}")
    suspend fun updateTransaction(@Path("id") id: String, @Body request: UpdateTransactionRequest): ApiResponse<Transaction>

    @DELETE("/api/v1/transactions/{id}")
    suspend fun deleteTransaction(@Path("id") id: String): ApiResponse<Map<String, String>>
}
