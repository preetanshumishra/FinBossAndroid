package com.preetanshumishra.FinBossAndroid.services

import com.preetanshumishra.FinBossAndroid.data.models.CreateTransactionRequest
import com.preetanshumishra.FinBossAndroid.data.models.Transaction
import com.preetanshumishra.FinBossAndroid.data.models.UpdateTransactionRequest
import com.preetanshumishra.FinBossAndroid.data.network.ApiService
import com.preetanshumishra.FinBossAndroid.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionService @Inject constructor(
    private val apiService: ApiService
) {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    suspend fun loadTransactions(): Resource<List<Transaction>> {
        return try {
            _isLoading.value = true
            _errorMessage.value = null
            val response = apiService.getTransactions()
            if (response.status == "success") {
                _transactions.value = response.data ?: emptyList()
                Resource.Success(response.data ?: emptyList())
            } else {
                val error = response.message ?: "Failed to load transactions"
                _errorMessage.value = error
                Resource.Error(error)
            }
        } catch (e: Exception) {
            val error = e.message ?: "Unknown error occurred"
            _errorMessage.value = error
            Resource.Error(error)
        } finally {
            _isLoading.value = false
        }
    }

    suspend fun createTransaction(request: CreateTransactionRequest): Resource<Transaction> {
        return try {
            _isLoading.value = true
            _errorMessage.value = null
            val response = apiService.createTransaction(request)
            if (response.status == "success") {
                response.data?.let { transaction ->
                    _transactions.value = _transactions.value + transaction
                }
                _errorMessage.value = null
                Resource.Success(response.data!!)
            } else {
                val error = response.message ?: "Failed to create transaction"
                _errorMessage.value = error
                Resource.Error(error)
            }
        } catch (e: Exception) {
            val error = e.message ?: "Unknown error occurred"
            _errorMessage.value = error
            Resource.Error(error)
        } finally {
            _isLoading.value = false
        }
    }

    suspend fun updateTransaction(id: String, request: UpdateTransactionRequest): Resource<Transaction> {
        return try {
            _isLoading.value = true
            _errorMessage.value = null
            val response = apiService.updateTransaction(id, request)
            if (response.status == "success") {
                response.data?.let { transaction ->
                    val index = _transactions.value.indexOfFirst { it.id == id }
                    if (index >= 0) {
                        val updatedList = _transactions.value.toMutableList()
                        updatedList[index] = transaction
                        _transactions.value = updatedList
                    }
                }
                _errorMessage.value = null
                Resource.Success(response.data!!)
            } else {
                val error = response.message ?: "Failed to update transaction"
                _errorMessage.value = error
                Resource.Error(error)
            }
        } catch (e: Exception) {
            val error = e.message ?: "Unknown error occurred"
            _errorMessage.value = error
            Resource.Error(error)
        } finally {
            _isLoading.value = false
        }
    }

    suspend fun deleteTransaction(id: String): Resource<String> {
        return try {
            _isLoading.value = true
            _errorMessage.value = null
            val response = apiService.deleteTransaction(id)
            if (response.status == "success") {
                _transactions.value = _transactions.value.filterNot { it.id == id }
                _errorMessage.value = null
                Resource.Success("Transaction deleted successfully")
            } else {
                val error = response.message ?: "Failed to delete transaction"
                _errorMessage.value = error
                Resource.Error(error)
            }
        } catch (e: Exception) {
            val error = e.message ?: "Unknown error occurred"
            _errorMessage.value = error
            Resource.Error(error)
        } finally {
            _isLoading.value = false
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
