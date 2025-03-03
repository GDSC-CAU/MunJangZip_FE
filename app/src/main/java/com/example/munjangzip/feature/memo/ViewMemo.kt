package com.example.munjangzip.feature.memo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R

@Composable
fun ViewMemoScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.book1), // 책 이미지 변경 필요
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "소년이 온다",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Text(
                text = "작가: 한강",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "출간일: 2014년 5월 19일",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "폭력과 혼란 속에서 자신의 신념을 잃지 않는 모습과 인간성을 유지하는 동호의 모습을 보았다.",
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "메모",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "동호의 용감하고 순수한 모습을 닮고 싶다는 생각이 들었다. 언제나 화이팅하자!!",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}
