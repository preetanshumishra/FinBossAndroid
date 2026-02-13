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
