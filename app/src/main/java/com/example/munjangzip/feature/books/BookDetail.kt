/*###################
#### 책 상세 페이지 ####
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
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.*
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import com.example.munjangzip.ui.theme.BrightYellow


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookDetailScreen(navController: NavController, bookId: Int) {
    val pagerState = rememberPagerState()
    val memos = listOf(
        "메모1: 가나다라마바사아자차카아아아낭러ㅕ아려ㅗㅑ몽래ㅑㅗ앨ㅈ달이라으ㅓ라ㅣㅣㄴ어ㅜ러ㅣㅈ",
        "메모2: 가나다라마바사아자차카아아아낭러ㅕ아려ㅗㅑ몽래ㅑㅗ앨ㅈ달이라으ㅓ라ㅣㅣㄴ어ㅜ러ㅣㅈ",
        "메모3: 가나다라마바사아자차카아아아낭러ㅕ아려ㅗㅑ몽래ㅑㅗ앨ㅈ달이라으ㅓ라ㅣㅣㄴ어ㅜ러ㅣㅈ"
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

                Spacer(modifier = Modifier.height(10.dp))

                // 스와이프로 메모 보기
                HorizontalPager(
                    count = memos.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 16.dp)
                ) { page ->
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White, //색상도 백에서 받아오기
                        shadowElevation = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(180.dp)
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                                //스와이프 시 회전&이동
                                translationX = -pageOffset * 300f
                                rotationY = pageOffset * 15f
                                alpha = lerp(
                                    start = 0.6f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // 메모 내용
                            Text(
                                text = memos[page],
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                color = Color.DarkGray,
                                style = TextStyle(fontWeight = FontWeight.Medium)
                            )
                            // 작성일 (하단에 고정)
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
                // 페이지 인디케이터
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    activeColor = Color.DarkGray,
                    inactiveColor = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))

                // 메모하기 버튼
                Button(
                    onClick = { /* 메모 기능 추가 해야 됨 */ },
                    modifier = Modifier
                        .width(110.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFF2D3)
                    )
                ) {
                    Text(text = "메모하기", fontSize = 14.sp, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
