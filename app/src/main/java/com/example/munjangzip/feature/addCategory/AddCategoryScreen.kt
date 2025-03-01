package com.example.munjangzip.feature.addCategory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGroundBubble

val LightYellow = Color(0xFFFFF2D3) // 연한 노란색

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryScreen(navController: NavController) {
    var text by remember { mutableStateOf("") } // 입력된 텍스트 상태

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // 뒤로 가기 버튼
                        Icon(
                            painter = painterResource(R.drawable.chevron_left),
                            contentDescription = "뒤로가기"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* 나중에 내정보 페이지로 이동 해야됨 */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_paw),
                            contentDescription = "내정보 페이지 이동"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent) // TopBar 투명
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackGroundBubble()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(50.dp))
                Box(contentAlignment = Alignment.Center) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("예시: 읽을 책 모음!", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier
                            .padding(16.dp)
                            .height(65.dp)
                            .width(240.dp),
                        shape = RoundedCornerShape(20.dp),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            color = Color.Gray
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightYellow,
                            unfocusedContainerColor = LightYellow,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Gray,
                            unfocusedTextColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Transparent, // 밑줄 제거
                            focusedIndicatorColor = Color.Transparent
                        ),
                    )
                    // 붕어빵 아이콘
                    Image(
                        painter = painterResource(R.drawable.fish),
                        contentDescription = "붕어빵 아이콘",
                        modifier = Modifier
                            .offset(y = (-40).dp)
                            .size(100.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                // 저장 버튼
                Button(
                    onClick = { /* 저장 기능 추가하기 */ },
                    modifier = Modifier
                        .width(120.dp)
                        .height(45.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.98f)
                    )
                ) {
                    Text(text = "저장하기",
                        fontSize = 16.sp,
                        color = Color.Gray)
                }
            }
        }
    }
}
