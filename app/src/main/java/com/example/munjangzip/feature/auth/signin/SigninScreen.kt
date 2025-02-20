package com.example.munjangzip.feature.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SignInScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(value = "", onValueChange = {}) //이메일
            OutlinedTextField(value = "", onValueChange = {}) //비밀번호
            Button(onClick = {}) {
                Text(text = "Sign In")
            }
            TextButton(onClick = { }) {
                Text(text = "Don't have an account? Sign up!")
            }
        }
    }
}