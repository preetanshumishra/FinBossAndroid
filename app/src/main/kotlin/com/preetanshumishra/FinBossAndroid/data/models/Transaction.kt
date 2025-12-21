package com.preetanshumishra.FinBossAndroid.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Transaction(
    val id: String,
    val userId: String,
    val type: String, // "income" or "expense"
    val amount: Double,
    val category: String,
    val description: String?,
    val date: Date,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date
)

data class CreateTransactionRequest(
    val type: String,
    val amount: Double,
    val category: String,
    val description: String? = null,
    val date: Date
)

data class UpdateTransactionRequest(
    val type: String? = null,
    val amount: Double? = null,
    val category: String? = null,
    val description: String? = null,
    val date: Date? = null
)
