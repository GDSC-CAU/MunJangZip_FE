package com.example.munjangzip.feature.createMemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.ui.BackGroundBubble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMemoPic(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackGroundBubble(R.drawable.bubble4)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(50.dp))
                // 사진 선택하기 버튼
                Button(
                    onClick = { /* 사진 선택로직 ㄱ */ },
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(30.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE5EFFD)
                    )
                ) {
                    Text(
                        text = "사진 선택",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                //사진 찍기 버튼
                Button(
                    onClick = { /* 사진 찍기 로직 ㄱ */ },
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(30.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFF2D3)
                    )
                ) {
                    Text(
                        text = "사진 찍기",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold

                    )
                }
            }
        }
    }
}
