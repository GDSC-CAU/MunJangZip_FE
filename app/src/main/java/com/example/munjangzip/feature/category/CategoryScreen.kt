package com.example.munjangzip.feature.category


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.theme.BrightYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
                shadowElevation = 8.dp
                //color =
            )
            {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BrightYellow
                    ),
                    title = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                "수정 님의" ,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "'말랑말랑' 도서관",
                                fontSize = 20.sp,
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold,

                            )
                        }

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
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Box(
                contentAlignment = Alignment.Center
            )
            {
                // 버튼 (뒤쪽 - 낮은 zIndex)
                ElevatedButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(65.dp)
                        .width(200.dp),
                        //.zIndex(0f), // 버튼의 zIndex를 낮게 설정

                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightYellow,
                        contentColor = Color.Gray,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                    onClick = {navController.navigate("addCategory")},//카테고리 추가 페이지로 이동
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "카테고리 추가하기",
                        modifier = Modifier.padding(start = 8.dp),//왼쪽 패딩 (붕어랑 안겹치게)
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    )
                }

                // 이미지 (앞쪽 - 높은 zIndex)
                Image(
                    painter = painterResource(R.drawable.fish),
                    contentDescription = null,
                    modifier = Modifier
                        .zIndex(1f)// 이미지의 zIndex를 높게 설정
                        .offset(x = (-105).dp)
                        .size(100.dp)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))
            BookCategoryPager()
            Spacer(modifier = Modifier.padding(13.dp))

            Button(
                modifier = Modifier
                    .height(37.dp)
                    .width(100.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.9f),
                    contentColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = { /* 책 보러가기로 이동 */ }
            ) {
                Text(
                    text = "책 보기",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }

}



