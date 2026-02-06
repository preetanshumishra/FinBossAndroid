package com.preetanshumishra.FinBossAndroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.preetanshumishra.FinBossAndroid.di.appDependencies
import com.preetanshumishra.FinBossAndroid.ui.navigation.SetupNavGraph
import com.preetanshumishra.FinBossAndroid.ui.theme.FinBossTheme
import kotlinx.coroutines.launch

/**
 * MainActivity is the entry point for the FinBoss app.
 *
 * Dagger Dependency Access:
 * - appDependencies is initialized in FinBossApplication.onCreate()
 * - We can access dependencies from anywhere using: appDependencies.authService
 * - This avoids exposing the AppComponent throughout the app
 * - Single point of access for all dependencies
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the authService from Dagger dependencies
        val authService = appDependencies.authService

        lifecycleScope.launch {
            authService.checkPersistedAuth()
        }

        setContent {
            FinBossTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(authService = authService)
                }
            }
        }
    }
}
