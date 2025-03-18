package com.example.munjangzip.feature.category

/*
CategoryScreen에서 실행할 pager을 표시하는 함수
 */

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlin.math.absoluteValue

@SuppressLint("RestrictedApi")
@Composable
fun BookCategoryPager(
    navController: NavController,
    categories: List<CategoryItem>,
    refreshCallback: () -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val categoryState by viewModel.categoryState.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }

    // Pull to Refresh 상태 관리
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    // 처음 화면이 로드될 때 API 호출
    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            isRefreshing = true

            viewModel.fetchCategories() // 📌 API 재요청
            isRefreshing = false
        }
    ) {
        val categories = categoryState?.result?.categoryList ?: emptyList()
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
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        alpha = lerp(0.5f, 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                    }
                    .clickable { navController.navigate("booklist/${category.categoryId}") },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val coverImage = category.recentBookCovers
                    if (coverImage != null && coverImage.isNotEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(coverImage),
                            contentDescription = "Book Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(Color.Black)
                        )
                    }

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
}
