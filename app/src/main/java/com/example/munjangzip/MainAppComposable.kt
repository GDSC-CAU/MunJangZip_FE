package com.example.munjangzip

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.munjangzip.feature.auth.signin.SignInScreen
import com.example.munjangzip.feature.category.CategoryScreen

@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "categorygi") {
            composable(route = "login") {
                SignInScreen(navController = navController)
            }

            composable(route = "register") {

            }

            composable(route = "category") {
                CategoryScreen(navController = navController)
            }

        }

    }
}
