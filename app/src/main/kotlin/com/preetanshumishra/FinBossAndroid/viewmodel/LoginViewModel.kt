package com.preetanshumishra.FinBossAndroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preetanshumishra.FinBossAndroid.services.AuthService
import com.preetanshumishra.FinBossAndroid.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * LoginViewModel handles login-related state and logic.
 *
 * Dagger Constructor Injection:
 * - Takes authService as a constructor parameter
 * - The ViewModelFactory handles creating instances with dependencies
 * - No special annotations needed - cleaner and more testable design
 */
class LoginViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            if (!validateInputs()) return@launch

            _isLoading.value = true
            _errorMessage.value = null

            when (val result = authService.login(_email.value, _password.value)) {
                is Resource.Success -> {
                    result.data?.let { authResponse ->
                        authService.setAuthenticatedUser(
                            user = authResponse.toUser(),
                            accessToken = authResponse.accessToken,
                            refreshToken = authResponse.refreshToken
                        )
                    }
                }
                is Resource.Error -> {
                    _errorMessage.value = result.message
                }
                is Resource.Loading -> {}
            }

            _isLoading.value = false
        }
    }

    private fun validateInputs(): Boolean {
        return when {
            _email.value.trim().isEmpty() -> {
                _errorMessage.value = "Email is required"
                false
            }
            _password.value.isEmpty() -> {
                _errorMessage.value = "Password is required"
                false
            }
            else -> true
        }
    }
}

// Extension function to convert AuthResponse to User
fun com.preetanshumishra.FinBossAndroid.data.models.AuthResponse.toUser() =
    com.preetanshumishra.FinBossAndroid.data.models.User(
        id = userId,
        email = email,
        firstName = firstName,
        lastName = lastName
    )
