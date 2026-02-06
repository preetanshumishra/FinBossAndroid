package com.preetanshumishra.FinBossAndroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preetanshumishra.FinBossAndroid.data.models.User
import com.preetanshumishra.FinBossAndroid.services.AuthService
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authService: AuthService
) : ViewModel() {

    val user: StateFlow<User?> = authService.user

    fun logout() {
        viewModelScope.launch {
            authService.clearAuthentication()
        }
    }
}
