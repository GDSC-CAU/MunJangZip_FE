package com.example.munjangzip.feature.category

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.theme.BrightYellow
import com.example.munjangzip.ui.theme.PeachYellow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 32.dp)
                //color =
            )
            {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = PeachYellow
                    ),
                    title = {
                        Text(
                            "말랑말랑 도서관"
                        )
                    },


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
            Spacer(modifier = Modifier.padding(vertical = 32.dp))
            /*
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy((-50).dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.fish),
                        contentDescription = null,

                    )
                    Button(
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            //containerColor = Color.Red,
                            containerColor = BrightYellow,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White
                        ),
                        onClick ={},
                        shape = RoundedCornerShape(10.dp),

                        ) {
                        Text(text= "카테고리 추가하기",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }
                }*/
            Box(
                contentAlignment = Alignment.Center
            )
            {
                // 버튼 (뒤쪽 - 낮은 zIndex)
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .zIndex(0f), // 버튼의 zIndex를 낮게 설정
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightYellow,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                    onClick = {},
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "   카테고리 추가하기",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                }

                // 이미지 (앞쪽 - 높은 zIndex)
                Image(
                    painter = painterResource(R.drawable.fish),
                    contentDescription = null,
                    modifier = Modifier
                        .zIndex(1f)// 이미지의 zIndex를 높게 설정
                        .offset(x = (-86).dp)
                )
            }


            BookCategoryPager()
        }

    }




}

@Composable
fun BackGround(modifier: Modifier = Modifier) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            alpha = 0.3F //투명도
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {
            Image(
                painter = painterResource(R.drawable.cat),
                contentDescription = null
            )
        }

    }

}

@SuppressLint("RestrictedApi")
@Composable
fun BookCategoryPager() { //카테고리를 가로 스크롤로 확인

    val images = listOf( //테스트를 위해 이미지를 불러옴
        R.drawable.book1,
        R.drawable.book2,
        R.drawable.book3,
    )
    val pagerState = rememberPagerState (pageCount = {
        images.size
    })

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // 화면 너비 가져오기
    val imageWidth = 200.dp // 이미지의 너비
    val imageSpacing = 10.dp // 이미지 간의 간격 (조절 가능)
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
        ) {
            Image(
                painter = painterResource(images[page]),
                contentDescription = "Book Image $page",
                contentScale = ContentScale.Crop
            )
        }

  /*
        Text(
            text = "Page: $page",
            modifier = Modifier.fillMaxWidth()
        )*/
    }

}
