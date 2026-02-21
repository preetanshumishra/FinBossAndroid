package com.preetanshumishra.FinBossAndroid.services

import com.preetanshumishra.FinBossAndroid.data.models.CategoryBreakdown
import com.preetanshumishra.FinBossAndroid.data.network.ApiService
import com.preetanshumishra.FinBossAndroid.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsService @Inject constructor(
    private val apiService: ApiService
) {
    private val _categoryBreakdown = MutableStateFlow<List<CategoryBreakdown>>(emptyList())
    val categoryBreakdown: StateFlow<List<CategoryBreakdown>> = _categoryBreakdown.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    suspend fun loadCategoryBreakdown(): Resource<List<CategoryBreakdown>> {
        return try {
            _isLoading.value = true
            _errorMessage.value = null
            val response = apiService.getCategoryBreakdown()
            if (response.status == "success") {
                _categoryBreakdown.value = response.data ?: emptyList()
                Resource.Success(response.data ?: emptyList())
            } else {
                val error = response.message ?: "Failed to load analytics"
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
