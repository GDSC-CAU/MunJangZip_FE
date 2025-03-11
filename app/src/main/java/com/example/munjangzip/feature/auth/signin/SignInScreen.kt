package com.example.munjangzip.feature.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.SignUpInputWidget
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.feature.createMemo.LightYellow
import com.example.munjangzip.ui.SimpleBackGround
import com.example.munjangzip.ui.theme.GrayishBlue
import com.example.munjangzip.ui.theme.PaleRed
@Composable
fun SignInScreen(navController: NavController) {
    val viewModel: SignInViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is SignInState.Success -> {
                Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                navController.navigate("category") {
                    popUpTo("login") { inclusive = true }
                }
            }
            is SignInState.Error -> {
                Toast.makeText(context, (uiState as SignInState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWidget(navController) }
    ) {
        SimpleBackGround()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Image(
                painter = painterResource(R.drawable.apptitle),
                contentDescription = "문장집"
            )

            SignUpInputWidget(
                textLabel = "이메일을 입력해주세요",
                textInputValue = email,
                onTextChange = { email = it },
                textFieldColor = LightYellow,
                fishImage = R.drawable.yellofish
            )

            SignUpInputWidget(
                textLabel = "비밀번호를 입력해주세요",
                textInputValue = password,
                onTextChange = { password = it },
                textFieldColor = Color.White,
                fishImage = R.drawable.fish_gray
            )

            Spacer(modifier = Modifier.padding(30.dp))

            Button(
                onClick = { viewModel.signIn(email, password) },
                modifier = Modifier.fillMaxWidth(0.3f)
            ) {
                Text(text = "로그인", fontSize = 16.sp)
            }

            if (uiState is SignInState.Loading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}
