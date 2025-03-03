package com.example.munjangzip.feature.category
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.munjangzip.R
import kotlin.math.absoluteValue
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign


@SuppressLint("RestrictedApi")
@Composable
fun BookCategoryPager(navController: NavController) { //카테고리를 가로 스크롤로 확인

    val images = listOf( //테스트를 위해 이미지를 불러옴
        R.drawable.book1,
        R.drawable.book2,
        R.drawable.book3,
    )
    val categories = listOf("최애 책들!", "공포오..", "역사 책 모음집 긴 텍스트 예시") // 테스트 - 카테고리
    val books = listOf(14, 20, 3) // 테스트 - 책 개수
    val memoCounts = listOf(67, 50, 90) // 테스트 - 메모 개수



    val pagerState = rememberPagerState (pageCount = {
        images.size
    })

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // 화면 너비 가져오기
    val imageWidth = 200.dp // 이미지의 너비
    val imageSpacing = 8.dp // 이미지 간의 간격 (조절 가능)
    val overlapWidth = screenWidth - imageWidth - imageSpacing // 겹쳐야 할 길이 계산

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = overlapWidth / 2), // 좌우 패딩 추가
        pageSpacing = imageSpacing // 페이지 간 간격 조절

    ) { page ->
        Card(
            Modifier
                .size(width = 200. dp, height = 290.dp)
                .padding(horizontal = imageSpacing / 2) // 좌우 간격 조정
                .graphicsLayer {
                    val pageOffset = ( //pageoffset 계산
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp( //페이드인/아웃 효과
                        start = 0.5f, //페이지 이동시 현재페이지에서 멀어질수록 투명해짐
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .clickable {
                    navController.navigate("booklist")
                }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // 책 이미지
                Image(
                    painter = painterResource(images[page]),
                    contentDescription = "Book Image $page",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                color = Color.Black.copy(alpha = 0.8f) // 블랙 오버레이 적용
                            )
                        }
                )

                // 책 정보표시
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 카테고리 제목
                    Text(
                        text = categories[page],
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // 등록된 책 개수 및 메모 개수
                    Text(
                        text = "등록된 책 : ${books[page]}권\n메모 : ${memoCounts[page]}개",
                        fontSize = 12.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}