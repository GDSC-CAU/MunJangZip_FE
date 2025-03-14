package com.example.munjangzip.feature.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.appbar.TopBarWidget
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.util.lerp
import com.example.munjangzip.feature.booklist.BookListViewModel
import com.example.munjangzip.feature.booklist.Paragraph


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookDetailScreen(navController: NavController, bookId: Int, viewModel: BookListViewModel = hiltViewModel()) {
    val bookDetailState by viewModel.bookDetailState.collectAsState()

    LaunchedEffect(bookId) {
        viewModel.fetchBookDetail(bookId)
    }

    val bookDetail = bookDetailState?.result

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWidget(navController) }
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
                if (bookDetail != null) {
                    Text(text = bookDetail.title ?: "제목 없음",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "작가: ${bookDetail.author}", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
                    Spacer(modifier = Modifier.height(15.dp))
                    AsyncImage(
                        model = bookDetail.coverImageUrl,
                        contentDescription = "책 표지",
                        contentScale = ContentScale.Fit, // 가로 비율 유지
                        modifier = Modifier
                            .height(320.dp)
                            .wrapContentWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    val paragraphs = bookDetail.paragraph.ifEmpty {
                        listOf(Paragraph(0, null, 0, "", null))
                    }
                    HorizontalPager(count = paragraphs.size) { page ->
                        val paragraph = paragraphs[page]
                        val bgColor = when (paragraph.color) {
                            1 -> R.drawable.memo_background1
                            2 -> R.drawable.memo_background3
                            3 -> R.drawable.memo_background2
                            4 -> R.drawable.memo_background4
                            5 -> R.drawable.memo_background5
                            else -> R.drawable.memo_default_background
                        }

                        //메모 크기조정 여기서
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            shadowElevation = 8.dp,
                            modifier = Modifier.fillMaxWidth(0.85f).height(190.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = bgColor),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Column(
                                    modifier = Modifier.padding(20.dp).fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    //메모 이미지 있으면 이미지표시, 없으면 content 표시
                                    if (!paragraph.imageUrl.isNullOrEmpty()) {
                                        AsyncImage(
                                            model = paragraph.imageUrl,
                                            contentDescription = "메모 이미지",
                                            modifier = Modifier.fillMaxWidth(0.8f).height(140.dp).clip(RoundedCornerShape(10.dp))
                                        )
                                    } else if (!paragraph.content.isNullOrEmpty()) {
                                        Text(
                                            text = paragraph.content,
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color.DarkGray
                                        )
                                    }
                                    //기본 배경일 경우 작성일 표시 x
                                    if (bgColor != R.drawable.memo_default_background) {
                                        Text(
                                            text = "작성일 : ${paragraph.create_at}",
                                            fontSize = 12.sp,
                                            color = Color.Gray,
                                            textAlign = TextAlign.End,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                    //페이지 인디케이터
                    HorizontalPagerIndicator(
                        pagerState = rememberPagerState(),
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                        activeColor = Color.DarkGray,
                        inactiveColor = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // 메모하기 버튼
                    Button(
                        onClick = { navController.navigate("selectMemo") },
                        modifier = Modifier.width(110.dp).height(40.dp).clip(RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF2D3))
                    ) {
                        Text(text = "메모하기", fontSize = 14.sp, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}