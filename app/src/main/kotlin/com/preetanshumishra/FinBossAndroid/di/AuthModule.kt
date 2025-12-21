package com.preetanshumishra.FinBossAndroid.di

import com.preetanshumishra.FinBossAndroid.data.local.TokenManager
import com.preetanshumishra.FinBossAndroid.data.network.ApiService
import com.preetanshumishra.FinBossAndroid.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthService(
        apiService: ApiService,
        tokenManager: TokenManager
    ): AuthService {
        return AuthService(apiService, tokenManager)
    }
}
