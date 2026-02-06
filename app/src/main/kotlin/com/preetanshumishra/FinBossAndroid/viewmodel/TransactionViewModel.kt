package com.preetanshumishra.FinBossAndroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preetanshumishra.FinBossAndroid.data.models.CreateTransactionRequest
import com.preetanshumishra.FinBossAndroid.data.models.Transaction
import com.preetanshumishra.FinBossAndroid.services.TransactionService
import com.preetanshumishra.FinBossAndroid.utils.Resource
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class TransactionViewModel(
    private val transactionService: TransactionService
) : ViewModel() {

    val transactions: StateFlow<List<Transaction>> = transactionService.transactions
    val isLoading: StateFlow<Boolean> = transactionService.isLoading
    val errorMessage: StateFlow<String?> = transactionService.errorMessage

    init {
        loadTransactions()
    }

    fun loadTransactions() {
        viewModelScope.launch {
            transactionService.loadTransactions()
        }
    }

    fun createTransaction(
        type: String,
        amount: Double,
        category: String,
        description: String,
        date: Date
    ) {
        viewModelScope.launch {
            val request = CreateTransactionRequest(
                type = type,
                amount = amount,
                category = category,
                description = description.ifBlank { null },
                date = date
            )
            transactionService.createTransaction(request)
        }
    }

    fun deleteTransaction(id: String) {
        viewModelScope.launch {
            transactionService.deleteTransaction(id)
        }
    }

    fun clearError() {
        transactionService.clearError()
    }
}
