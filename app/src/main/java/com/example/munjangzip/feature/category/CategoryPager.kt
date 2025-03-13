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
import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import coil.compose.rememberAsyncImagePainter

@SuppressLint("RestrictedApi")
@Composable
fun BookCategoryPager(navController: NavController, categories: List<CategoryItem>) {
    val pagerState = rememberPagerState(pageCount = { categories.size })

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageWidth = 200.dp
    val imageSpacing = 8.dp
    val overlapWidth = screenWidth - imageWidth - imageSpacing

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = overlapWidth / 2),
        pageSpacing = imageSpacing
    ) { page ->
        val category = categories[page]

        Card(
            Modifier
                .size(width = 200.dp, height = 290.dp)
                .padding(horizontal = imageSpacing / 2)
                .graphicsLayer {
                    val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    alpha = lerp(0.5f, 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                }
                .clickable { navController.navigate("booklist/${category.categoryId}") },
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), //그림자 없애고
            colors = CardDefaults.cardColors(containerColor = Color.Black) // 완전 블랙으로
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // 책 이미지 or 검은 화면
                val coverImage = category.recentBookCovers
                if (coverImage != null) {
                    if (coverImage.isNotEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(coverImage),
                            contentDescription = "Book Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        //블랙 오버레이
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black)
                        )
                    }
                }

                // 카테고리 정보 표시
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = category.categoryName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "등록된 책 : ${category.bookCount}권\n메모 : ${category.memoCount}개",
                        fontSize = 12.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
