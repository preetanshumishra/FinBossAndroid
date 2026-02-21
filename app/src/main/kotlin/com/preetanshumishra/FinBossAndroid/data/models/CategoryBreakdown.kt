package com.preetanshumishra.FinBossAndroid.data.models

data class CategoryBreakdown(
    val category: String,
    val amount: Double,
    val percentage: Double,
    val transactionCount: Int
)
