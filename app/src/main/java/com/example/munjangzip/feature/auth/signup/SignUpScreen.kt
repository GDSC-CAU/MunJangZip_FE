package com.example.munjangzip.feature.auth.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.SignUpInputWidget
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.feature.createMemo.LightYellow
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.BackGroundBubble
import com.example.munjangzip.ui.SimpleBackGround
import com.example.munjangzip.ui.theme.GrayishBlue
import com.example.munjangzip.ui.theme.PaleRed
import okhttp3.internal.wait
val LightPink = Color(0xFFFFECF2)

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()

    var email by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var libraryName by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is SignUpState.Success -> {
                navController.navigate("category") {
                    popUpTo("login") { inclusive = true }
                    popUpTo("signup") { inclusive = true }
                }
            }
            is SignUpState.Error -> {
                Toast.makeText(context, (uiState as SignUpState.Error).message, Toast.LENGTH_SHORT).show()
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
                textLabel = "닉네임을 입력해주세요",
                textInputValue = nickname,
                onTextChange = { nickname = it },
                textFieldColor = GrayishBlue,
                fishImage = R.drawable.fish_blue
            )
            SignUpInputWidget(
                textLabel = "비밀번호를 입력해주세요",
                textInputValue = password,
                onTextChange = { password = it },
                textFieldColor = Color.White,
                fishImage = R.drawable.fish_gray,

            )
            SignUpInputWidget(
                textLabel = "당신의 도서관 이름은?",
                textInputValue = libraryName,
                onTextChange = { libraryName = it },
                textFieldColor = LightPink,
                fishImage = R.drawable.fish_pink
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    when {
                        email.isBlank() || nickname.isBlank() || password.isBlank() || libraryName.isBlank() -> {
                            // 하나라도 안쓰면 토스트출력
                            Toast.makeText(context, "모든 필드를 입력해주세요!", Toast.LENGTH_SHORT).show()
                        }
                        password.length < 8 -> {
                            // 비번 8자리 이하면 토스트 출력
                            Toast.makeText(context, "비밀번호는 8자 이상 입력해주세요!", Toast.LENGTH_SHORT).show()

                        }
                        !isValidEmail(email) -> {
                            //이메일 형식 안맞으면 토스트 출력
                            Toast.makeText(context, "올바른 이메일 형식을 입력해주세요!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            // 다 만족하면 회원가입 진행
                            viewModel.signUp(nickname, libraryName, email, password)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "회원가입", fontSize = 16.sp)
            }

            if (uiState is SignUpState.Loading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}
//이메일 형식 맞는지 검사하는 함수
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}