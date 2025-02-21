package com.example.munjangzip.feature.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.theme.BrightYellow
import com.example.munjangzip.ui.theme.PeachYellow

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

