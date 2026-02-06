package com.preetanshumishra.FinBossAndroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.preetanshumishra.FinBossAndroid.di.appDependencies

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm: ViewModel = when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(appDependencies.authService)
            RegisterViewModel::class.java -> RegisterViewModel(appDependencies.authService)
            HomeViewModel::class.java -> HomeViewModel(appDependencies.authService)
            TransactionViewModel::class.java -> TransactionViewModel(appDependencies.transactionService)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
        return vm as T
    }
}
