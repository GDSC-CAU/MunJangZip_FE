/*###################
##   책 상세 페이지   ##
###################*/
package com.example.munjangzip.feature.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.appbar.TopBarWidget
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.*


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookDetailScreen(navController: NavController, bookId: Int) {
    val pagerState = rememberPagerState()
    val memos = listOf(
        "메모1 가나다라마맛 마아아나앙 낭낭림ㅇㄹ",
        "메모2 가나다라마맛 마아아나앙 낭낭림ㅇㄹ",
        "메모3 가나다라마맛 마아아나앙 낭낭림ㅇㄹ"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController)
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            BackGround()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 책 정보 - 임시로 표시
                Text(text = "소년이 온다", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "작가: 한강", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
                Text(text = "출간일: 2014년 5월 19일", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(15.dp))

                Image(
                    painter = painterResource(R.drawable.book1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(320.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 메모 스와이프 카드
                HorizontalPager(
                    count = memos.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) { page ->
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.9f))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = memos[page],
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                color = Color.DarkGray,
                                style = TextStyle(fontWeight = FontWeight.Medium)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "작성일 : 2024.07.27",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 페이지 인디케이터
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    activeColor = Color.DarkGray,
                    inactiveColor = Color.LightGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 메모하기 버튼
                Button(
                    onClick = { /* 메모하기 기능 추가 */ },
                    modifier = Modifier
                        .width(120.dp)
                        .height(45.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFF2D3)
                    )
                ) {
                    Text(text = "메모하기", fontSize = 14.sp, color = Color.DarkGray)
                }
            }
        }
    }
}
