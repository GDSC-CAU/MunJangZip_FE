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
import coil.compose.rememberAsyncImagePainter

@SuppressLint("RestrictedApi")
@Composable
fun BookCategoryPager(navController: NavController, categories: List<CategoryItem>?) {
    val pagerState = rememberPagerState(pageCount = { categories?.size ?: 0 })

    if (categories.isNullOrEmpty()) {
        // 데이터가 없을 경우 기본 메시지 표시
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("카테고리가 없습니다.", color = Color.Gray, fontSize = 16.sp)
        }
        return
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 40.dp),
        pageSpacing = 8.dp
    ) { page ->
        val category = categories[page]

        Card(
            Modifier
                .size(width = 200.dp, height = 290.dp)
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate("booklist") }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (!category.recentBookCovers.isNullOrEmpty()) {
                    // ✅ 책 이미지가 있을 때
                    Image(
                        painter = rememberAsyncImagePainter(category.recentBookCovers),
                        contentDescription = "Book Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // ✅ 책 이미지가 없을 때 (검은색 배경)
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.8f))
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 80.dp, start = 16.dp, end = 16.dp),
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
