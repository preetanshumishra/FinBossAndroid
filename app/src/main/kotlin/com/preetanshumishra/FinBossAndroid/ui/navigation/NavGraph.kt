package com.preetanshumishra.FinBossAndroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.preetanshumishra.FinBossAndroid.services.AuthService
import com.preetanshumishra.FinBossAndroid.ui.screens.HomeScreen
import com.preetanshumishra.FinBossAndroid.ui.screens.LoginScreen
import com.preetanshumishra.FinBossAndroid.ui.screens.RegisterScreen
import com.preetanshumishra.FinBossAndroid.ui.screens.TransactionListScreen

@Composable
fun SetupNavGraph(authService: AuthService) {
    val isLoggedIn by authService.isLoggedIn.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("transactions") {
            TransactionListScreen()
        }
    }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("home") {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }
    }
}
