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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.ui.BackGroundBubble

val LightYellow = Color(0xFFFFF2D3) // 연한 노란색
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryScreen(navController: NavController, viewModel: AddCategoryViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWidget(navController) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackGroundBubble(R.drawable.bubble)
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(50.dp))

                Box(contentAlignment = Alignment.Center) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("예시: 읽을 책 모음!", color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.padding(16.dp).height(65.dp).width(240.dp),
                        shape = RoundedCornerShape(20.dp),
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.Gray),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightYellow,
                            unfocusedContainerColor = LightYellow,
                            cursorColor = Color.Black ,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                    )
                    Image(
                        painter = painterResource(R.drawable.fish),
                        contentDescription = "붕어빵 아이콘",
                        modifier = Modifier.offset(y = (-40).dp).size(100.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                Button(
                    onClick = { viewModel.addCategory(text) },
                    modifier = Modifier.width(120.dp).height(45.dp).shadow(8.dp, RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.98f))
                ) {
                    Text(text = "저장하기", fontSize = 16.sp, color = Color.Gray)
                }
            }
        }
    }
}
