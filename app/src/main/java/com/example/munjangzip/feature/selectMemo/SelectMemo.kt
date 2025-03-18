package com.example.munjangzip.feature.selectMemo


//글쓰기를 할지, 사진을 추가할지 메모 유형을 선택하는 화면
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGroundBubble
import com.example.munjangzip.appbar.TopBarWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectMemo(navController: NavController, bookId : Int) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackGroundBubble(R.drawable.bubble3)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(25.dp))

                // "바로 글쓰기" 버튼
                MemoButton(
                    text = "바로 글쓰기",
                    color = Color(0xFFFFF2D3),
                    fishIcon = R.drawable.fish,
                    onClick = { navController.navigate("createMemo?bookId=$bookId") }
                )

                Spacer(modifier = Modifier.padding(25.dp))
                // "사진 추가하기" 버튼
                MemoButton(
                    text = "사진 추가하기",
                    color = Color(0xFFE5EFFD),
                    fishIcon = R.drawable.fish_blue,
                    onClick = { navController.navigate("createMemoPic?bookId=$bookId") }
                )

                Spacer(modifier = Modifier.padding(25.dp))

                // "업데이트 예정!" 버튼
                MemoButton(
                    text = "업데이트 예정!",
                    color = Color(0xFFF5F5F5),
                    fishIcon = R.drawable.fish_gray,
                    dashed = true,
                    onClick = { }
                )
            }
        }
    }
}

// 메모 버튼 컴포저블
@Composable
fun MemoButton(
    text: String,
    color: Color,
    fishIcon: Int,
    dashed: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.6f)
            .height(65.dp)
    ) {
        ElevatedButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f)
                .shadow(8.dp, shape = RoundedCornerShape(30.dp))
            ,
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = Color.Gray,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(start = 20.dp),  // 왼쪽 패딩 (붕어랑 안 겹치게)
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            )
        }

        if (dashed) {
            // 점선 테두리
            Canvas(modifier = Modifier.matchParentSize()) {
                drawRoundRect(
                    color = Color.LightGray,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    ),
                    size = this.size,
                    cornerRadius = CornerRadius(30.dp.toPx())
                )
            }
        }

        // 붕어빵
        Image(
            painter = painterResource(fishIcon),
            contentDescription = null,
            modifier = Modifier
                .zIndex(1f)
                .offset(x = (-80).dp)
                .size(120.dp)
        )
    }
}
