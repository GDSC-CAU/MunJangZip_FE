package com.example.munjangzip.feature.booklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.theme.BrightYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavController, categoryId: Int, viewModel: BookListViewModel = hiltViewModel()) {
    val userState by viewModel.userState.collectAsState()
    val bookListState by viewModel.bookListState.collectAsState()

    LaunchedEffect(categoryId) {
        viewModel.fetchBooksByCategory(categoryId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 32.dp),
                shadowElevation = 8.dp
            ) {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BrightYellow
                    ),
                    title = {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Image(
                                    painter = painterResource(R.drawable.chevron_left),
                                    contentDescription = "뒤로 가기",
                                )
                            }
                            Spacer(modifier = Modifier.padding(12.dp))
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "${userState?.result?.nickName ?: "사용자"} 님의",
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "'${userState?.result?.libraryName ?: ""}' 도서관",
                                    fontSize = 20.sp,
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            BackGround()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val books = bookListState?.result?.books ?: emptyList()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(12.dp)
            ) {
                if (books.isEmpty()) {
                    // 📌 책이 없을 경우 책 추가 버튼만 표시
                    item {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(100.dp, 160.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.addbook),
                                contentDescription = "책 추가 버튼",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.clickable {
                                    navController.navigate("takephoto/$categoryId")
                                }
                            )
                        }
                    }
                } else {
                    // 📌 책 목록 표시
                    items(books.size) { index ->
                        val book = books[index]
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(100.dp, 160.dp)
                                .clickable { navController.navigate("bookDetail/${book.bookId}") }
                        ) {
                            AsyncImage(
                                model = book.bookImage,
                                contentDescription = "Book Image ${book.title}",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    // 📌 마지막에 책 추가 버튼 항상 추가
                    item {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(100.dp, 160.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.addbook),
                                contentDescription = "책 추가 버튼",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.clickable {
                                    navController.navigate("takephoto/$categoryId")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
