package com.preetanshumishra.FinBossAndroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preetanshumishra.FinBossAndroid.data.models.CategoryBreakdown
import com.preetanshumishra.FinBossAndroid.services.AnalyticsService
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnalyticsViewModel(
    private val analyticsService: AnalyticsService
) : ViewModel() {

    val categoryBreakdown: StateFlow<List<CategoryBreakdown>> = analyticsService.categoryBreakdown
    val isLoading: StateFlow<Boolean> = analyticsService.isLoading
    val errorMessage: StateFlow<String?> = analyticsService.errorMessage

    init {
        loadCategoryBreakdown()
    }

    fun loadCategoryBreakdown() {
        viewModelScope.launch {
            analyticsService.loadCategoryBreakdown()
        }
    }

    fun clearError() {
        analyticsService.clearError()
    }
}
