package com.preetanshumishra.FinBossAndroid.di

import android.content.Context
import com.preetanshumishra.FinBossAndroid.data.local.TokenManager
import com.preetanshumishra.FinBossAndroid.data.network.ApiService
import com.preetanshumishra.FinBossAndroid.services.AuthService
import com.preetanshumishra.FinBossAndroid.services.TransactionService

/**
 * AppDependencies is a container for all singleton dependencies.
 *
 * Why do we need this?
 * - Instead of keeping a reference to the entire AppComponent throughout the app,
 *   we create this container to expose only the dependencies we need
 * - It acts as a "dependency store" that's easy to access from anywhere
 * - It makes the code cleaner and more organized
 *
 * How does it work?
 * 1. We create it once in the Application class
 * 2. Store a reference to it globally
 * 3. Access dependencies from this container when needed (e.g., in Activities)
 *
 * Example usage:
 * In MainActivity:
 *     val authService = appDependencies.authService
 *     val tokenManager = appDependencies.tokenManager
 *
 * This is much cleaner than:
 *     appComponent.inject(this)  // Which requires making activities implement interfaces
 */
class AppDependencies(appComponent: AppComponent) {
    // These properties give us direct access to the singletons
    // All of these are created once and cached by Dagger
    val authService: AuthService = appComponent.authService
    val transactionService: TransactionService = appComponent.transactionService
    val tokenManager: TokenManager = appComponent.tokenManager
    val apiService: ApiService = appComponent.apiService
}

/**
 * This is a global reference to AppDependencies.
 * We store it here so we can access it from anywhere in the app without passing it around.
 *
 * WARNING: This uses a lateinit variable which throws an exception if accessed before initialization.
 * Make sure to initialize it early in FinBossApplication.onCreate()
 */
private lateinit var _appDependencies: AppDependencies

/**
 * Access dependencies from anywhere in the app using: appDependencies.authService
 */
val appDependencies: AppDependencies
    get() = _appDependencies

/**
 * Initialize the global dependencies container. Call this in Application.onCreate()
 */
fun initializeAppDependencies(dependencies: AppDependencies) {
    _appDependencies = dependencies
}
