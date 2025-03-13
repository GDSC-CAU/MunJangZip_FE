package com.example.munjangzip.feature.booklist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.munjangzip.R
@Composable
fun BookListGrid(navController: NavController, viewModel: BookListViewModel, categoryId: Int) {


    // ✅ 서버에서 책 목록 가져오기
    LaunchedEffect(categoryId) {
        viewModel.fetchBookList(categoryId = categoryId) // 원하는 카테고리 ID 설정
    }

    val bookListState by viewModel.bookListState.collectAsState()
    // ✅ 서버에서 가져온 책 목록 사용
    val books = bookListState.result.books

// ✅ books가 비어 있을 경우 예외 방지
    if (books.isEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(1){
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 40.dp, height = 160.dp)

                    ){
                        Image(
                            painter = painterResource(R.drawable.addbook),
                            contentDescription = "책 추가 버튼",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clickable {
                                //navController.navigate("takephoto")
                                navController.navigate("takephoto/$categoryId")
                            }
                        )
                    }
                }

            })

    }
    else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(books.size + 1) { index ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 40.dp, height = 160.dp)
                            .clickable {
                                if (index < books.size) {
                                    //navController.navigate("bookDetail/${index}")
                                    navController.navigate("bookDetail/${books[index].bookId}")
                                }
                            }
                    ) {
                        if (index == books.size) {
                            Image(
                                painter = painterResource(R.drawable.addbook),
                                contentDescription = "책 추가 버튼",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.clickable {
                                    //navController.navigate("takephoto")
                                    navController.navigate("takephoto/$categoryId")

                                }
                            )
                        } else {

                            // ✅ 서버에서 가져온 책 표지 이미지 표시 (Coil 사용)
                            AsyncImage(
                                model = books[index].bookImage,
                                contentDescription = "Book Image ${books[index].title}",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        )
    }

}
