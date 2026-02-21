package com.preetanshumishra.FinBossAndroid.di

import com.preetanshumishra.FinBossAndroid.data.local.TokenManager
import com.preetanshumishra.FinBossAndroid.data.network.ApiService
import com.preetanshumishra.FinBossAndroid.services.AuthService
import com.preetanshumishra.FinBossAndroid.services.AnalyticsService
import com.preetanshumishra.FinBossAndroid.services.TransactionService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthService(
        apiService: ApiService,
        tokenManager: TokenManager
    ): AuthService {
        return AuthService(apiService, tokenManager)
    }

    @Provides
    @Singleton
    fun provideTransactionService(
        apiService: ApiService
    ): TransactionService {
        return TransactionService(apiService)
    }

    @Provides
    @Singleton
    fun provideAnalyticsService(apiService: ApiService): AnalyticsService = AnalyticsService(apiService)
}
