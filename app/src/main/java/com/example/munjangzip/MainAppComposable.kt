package com.example.munjangzip

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.munjangzip.feature.auth.signin.SignInScreen
import com.example.munjangzip.feature.booklist.BookListScreen
import com.example.munjangzip.feature.category.CategoryScreen
import com.example.munjangzip.feature.savebook.TakePhotoPage

@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "takephoto") {
            composable(route = "login") {
                SignInScreen(navController = navController)
            }

            composable(route = "register") {

            }

            composable(route = "category") {
                CategoryScreen(navController = navController)
            }

            composable(route = "booklist") {
                BookListScreen(navController = navController)
            }

            composable(route = "takephoto") {
                TakePhotoPage(navController = navController)
            }
        }

    }
}
