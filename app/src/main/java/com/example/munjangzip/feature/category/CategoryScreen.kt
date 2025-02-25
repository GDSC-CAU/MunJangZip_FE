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
import com.example.munjangzip.ui.theme.PeachYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 32.dp),
                shadowElevation = 8.dp
                //color =
            )
            {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = PeachYellow
                    ),
                    title = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                "수정 님의" , fontSize = 12.sp
                            )
                            Text(
                                " '말랑말랑' 도서관"
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
                        .height(49.dp)
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
                            fontWeight = FontWeight(500),
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
                        .offset(x = (-86).dp)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))
            BookCategoryPager()
        }

    }




}



