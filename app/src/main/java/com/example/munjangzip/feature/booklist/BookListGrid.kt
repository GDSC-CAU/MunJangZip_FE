package com.example.munjangzip.feature.booklist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.munjangzip.R
import coil.compose.rememberAsyncImagePainter
import com.example.munjangzip.R.drawable.addbook

@Composable
fun BookListGrid(navController: NavController, viewModel: BookListViewModel, categoryId: Int) {

    LaunchedEffect(categoryId) {
        viewModel.fetchBooksByCategory(categoryId)
    }

    val bookListState by viewModel.bookListState.collectAsState()
    val books = bookListState?.result?.books ?: emptyList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            if (books.isEmpty()) {
                // 책이 없을 경우 추가 버튼만 표시
                item {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 100.dp, height = 160.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.addbook),
                            contentDescription = "책 추가 버튼",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clickable {
                                Log.d("NavigationDebug", "Navigating to takephoto/$categoryId")
                                navController.navigate("takephoto/$categoryId")

                            }
                        )
                    }
                }
            } else {
                // 책 목록 표시
                items(books.size) { index ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 100.dp, height = 160.dp)
                            .clickable {
                                navController.navigate("bookDetail/${books[index].bookId}")
                            }
                    ) {
                        AsyncImage(
                            model = books[index].bookImage,
                            contentDescription = "Book Image ${books[index].title}",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                // 마지막에 책 추가 버튼 표시
                item {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 100.dp, height = 160.dp)
                    ) {
                        Image(
                            painter = painterResource(addbook),
                            contentDescription = "책 추가 버튼",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clickable {
                                Log.d("NavigationDebug", "Navigating to takephoto/$categoryId")
                                navController.navigate("takephoto/$categoryId")

                            }
                        )
                    }
                }
            }
        }
    )
}