package com.preetanshumishra.FinBossAndroid.data.models

import java.util.Date

data class UpdateTransactionRequest(
    val type: String? = null,
    val amount: Double? = null,
    val category: String? = null,
    val description: String? = null,
    val date: Date? = null
)
