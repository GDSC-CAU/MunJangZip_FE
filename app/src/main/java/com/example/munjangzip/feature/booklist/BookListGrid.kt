package com.example.munjangzip.feature.booklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.munjangzip.R
@Composable
fun BookListGrid(navController: NavController) {
    val images = listOf( //테스트를 위해 이미지를 불러옴
        R.drawable.book1,
        R.drawable.book2,
        R.drawable.book3,
        R.drawable.book4
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(images.size + 1) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 40.dp, height = 160.dp)
                        .clickable {
                            if (index < images.size) {
                                navController.navigate("bookDetail/${index}")
                            }
                        }
                ) {
                    if (index == images.size) {
                        Image(
                            painter = painterResource(R.drawable.addbook),
                            contentDescription = "책 추가 버튼",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clickable { navController.navigate("takephoto") }
                        )
                    } else {
                        Image(
                            painter = painterResource(images[index]),
                            contentDescription = "Book Image $index",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
        }
    )
}
