package com.example.munjangzip.feature.auth

/*
로그인, 회원가입 화면으로 가기전 스타트 화면
로그인을 할지 회원가입 할지 선텍
*/
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.StartWidget
import com.example.munjangzip.appbar.SignUpInputWidget
import com.example.munjangzip.ui.SimpleBackGround
import com.example.munjangzip.ui.theme.BrightYellow
import com.example.munjangzip.ui.theme.GrayishBlue

@Composable
fun StartScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleBackGround()
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Image(
                painter = painterResource(R.drawable.apptitle),
                contentDescription = "고양이",
                //modifier = Modifier.size(300.dp)
            )
            StartWidget(
                navController = navController,
                textLabel = "회원가입",
                buttonColor = BrightYellow,
                fishImage = R.drawable.yellofish,
                navigationPage = "register"
            )
            StartWidget(
                navController = navController,
                textLabel = "로그인",
                buttonColor = GrayishBlue,
                fishImage = R.drawable.fish_blue,
                navigationPage = "login"
            )
        }
    }
}