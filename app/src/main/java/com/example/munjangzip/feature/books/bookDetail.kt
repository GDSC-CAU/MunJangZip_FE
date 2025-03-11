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
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookDetailScreen(navController: NavController, bookId: Int) {
    val pagerState = rememberPagerState()

    // 백엔드에서 받아온 데이터 예시
    val paragraphs = listOf(
        mapOf("content" to "직접 작성 예시", "ImageUrl" to null, "color" to 1, "createAt" to "2024-07-27"),
        mapOf("content" to null, "ImageUrl" to "https://example.com/image1.jpg", "color" to 2, "createAt" to "2024-07-28"),
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
                // 책 정보 임시 표시
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

                //스와이프로 메모 보기
                HorizontalPager(
                    count = paragraphs.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(horizontal = 16.dp)
                ) { page ->
                    val paragraph = paragraphs[page]
                    val content = paragraph["content"] as String?
                    val imageUrl = paragraph["ImageUrl"] as String?
                    val bgColor = when (paragraph["color"]) {
                        1 -> R.drawable.memo_background1 // 노란색
                        2 -> R.drawable.memo_background3 // 회색
                        3 -> R.drawable.memo_background2 // 분홍색
                        4 -> R.drawable.memo_background4 // 하늘색
                        5 -> R.drawable.memo_background5 // 연두색
                        else -> R.drawable.memo_default_background
                    }

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        shadowElevation = 8.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(200.dp)
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
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
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = bgColor),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                // 이미지 url 있으면 이미지 출력
                                if (imageUrl != null) {
                                    Image(
                                        painter = rememberAsyncImagePainter(imageUrl),
                                        contentDescription = "메모 이미지",
                                        modifier = Modifier
                                            .fillMaxWidth(0.8f)
                                            .height(140.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                    )
                                }

                                // 텍스트 있는 경우 텍스트 출력
                                if (!content.isNullOrEmpty()) {
                                    Text(
                                        text = content,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        color = Color.DarkGray,
                                        style = TextStyle(fontWeight = FontWeight.Medium)
                                    )
                                }

                                // 작성일
                                Text(
                                    text = "작성일 : ${paragraph["createAt"]}",
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
                    onClick = { navController.navigate("selectMemo") },
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
