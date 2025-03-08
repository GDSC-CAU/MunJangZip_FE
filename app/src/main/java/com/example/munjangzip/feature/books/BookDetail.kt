/*###################
#### 책 상세 페이지 ####
###################*/
package com.example.munjangzip.feature.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.appbar.TopBarWidget
import com.google.accompanist.pager.*
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookDetailScreen(navController: NavController, bookId: Int) {
    val pagerState = rememberPagerState()

    // 백엔드에서 받아온 데이터 예시
    val paragraphs = listOf(
        mapOf("content" to "메모1: 가나다라마바사아자차카아아아낭러ㅕ아려ㅗㅑ몽래ㅑㅗ앨ㅈ달이라으ㅓ라ㅣㅣㄴ어ㅜ러ㅣㅈ", "imageUrl" to "url1", "color" to 1, "createdAt" to "2024-07-27"),
        mapOf("content" to "메모2: 가나다라마바사아자차카아아아낭러ㅕ아려ㅗㅑ몽래ㅑㅗ앨ㅈ달이라으ㅓ라ㅣㅣㄴ어ㅜ러ㅣㅈ", "imageUrl" to "url2", "color" to 2, "createdAt" to "2024-07-28"),
        mapOf("content" to "메모3: 가나다라마바사아자차카아아아낭러ㅕ아려ㅗㅑ몽래ㅑㅗ앨ㅈ달이라으ㅓ라ㅣㅣㄴ어ㅜ러ㅣㅈ", "imageUrl" to "url3", "color" to 3, "createdAt" to "2024-07-29")
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
                    count = paragraphs.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 16.dp)
                ) { page ->
                    val paragraph = paragraphs[page]
                    val bgColor = when (paragraph["color"]) {
                        1 -> R.drawable.memo_background1 //노란색
                        2 -> R.drawable.memo_background2 //분홍색
                        3 -> R.drawable.memo_background3 //회색
                        4 -> R.drawable.memo_background4 //하늘색
                        5 -> R.drawable.memo_background5 //연두색
                        else -> R.drawable.memo_default_background
                    }
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        shadowElevation = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(180.dp)
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                                // 스와이프 시 회전 & 이동 효과
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
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            // 메모 색상 배경
                            Image(
                                painter = painterResource(id = bgColor),
                                contentDescription = null,
                                contentScale = ContentScale.Crop, //크기 조정
                                modifier = Modifier.fillMaxSize()
                            )

                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxSize(),

                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                // 메모 내용
                                Text(
                                    text = paragraph["content"] as String,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.DarkGray,
                                    style = TextStyle(fontWeight = FontWeight.Medium)
                                )
                                // 작성일 (하단에 고정)
                                Text(
                                    text = "작성일 : ${paragraph["createdAt"]}",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
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
                    onClick = { navController.navigate("createMemo")}, //메모 생성페이지로 이동
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
