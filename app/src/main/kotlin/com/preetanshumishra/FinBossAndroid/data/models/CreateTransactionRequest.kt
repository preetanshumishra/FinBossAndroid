package com.preetanshumishra.FinBossAndroid.data.models

import java.util.Date

data class CreateTransactionRequest(
    val type: String,
    val amount: Double,
    val category: String,
    val description: String? = null,
    val date: Date
)
