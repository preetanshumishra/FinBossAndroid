package com.preetanshumishra.FinBossAndroid.di

import android.content.Context
import com.preetanshumishra.FinBossAndroid.MainActivity
import com.preetanshumishra.FinBossAndroid.data.local.TokenManager
import com.preetanshumishra.FinBossAndroid.data.network.ApiService
import com.preetanshumishra.FinBossAndroid.services.AuthService
import com.preetanshumishra.FinBossAndroid.services.TransactionService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * AppComponent is the top-level Dagger component that defines the dependency graph.
 *
 * What is this?
 * - A Dagger @Component is like a factory that knows how to create and provide all dependencies
 * - It combines all the @Modules (AppModule, AuthModule) and builds the dependency graph
 * - @Singleton scope means all dependencies provided are single instances shared across the app
 *
 * What does the code do?
 * 1. modules = [...] - Tells Dagger which modules to use for providing dependencies
 * 2. @BindsInstance - Allows us to pass Context manually when creating the component
 * 3. fun inject(activity: MainActivity) - Tells Dagger which activities need dependency injection
 * 4. Properties like authService, tokenManager - Expose dependencies for access outside Dagger
 */
@Singleton
@Component(modules = [AppModule::class, AuthModule::class])
interface AppComponent {

    /**
     * @BindsInstance: This marks that Context comes from outside Dagger
     * - We manually pass the context when creating the component
     * - This is necessary because Dagger can't create Context itself
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    /**
     * This method tells Dagger to inject dependencies into MainActivity
     * When we call appComponent.inject(activity), Dagger will:
     * 1. Look for @Inject fields in MainActivity
     * 2. Resolve their dependencies using the modules
     * 3. Set the fields on the activity
     */
    fun inject(activity: MainActivity)

    // ===== Properties for accessing dependencies =====
    // These allow AppDependencies to access the singletons
    val authService: AuthService
    val transactionService: TransactionService
    val tokenManager: TokenManager
    val apiService: ApiService
}
