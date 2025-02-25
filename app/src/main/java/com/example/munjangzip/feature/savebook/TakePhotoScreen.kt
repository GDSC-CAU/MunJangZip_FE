package com.example.munjangzip.feature.savebook

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.theme.Gray10
import com.example.munjangzip.ui.theme.Ivory

@Composable
fun TakePhotoPage(navController: NavController) {
    Scaffold() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            BackGround()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .size(width = 250.dp, height = 85.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .zIndex(0f),
                    colors = CardDefaults.cardColors(
                        containerColor = Ivory
                    )

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize(),

                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("책의 ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("바코드를 촬영")
                                }
                                append("하면\n자동으로 책이 등록 돼요!")
                            },
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 15.sp,
                                color = Gray10
                            )
                        )
                    }

                }

                Image(
                    painter = painterResource(R.drawable.fish),
                    contentDescription = null,
                    modifier = Modifier
                        .zIndex((1f))
                        .offset(y = (-45).dp)
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))
            ElevatedButton(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Gray10
                )
            ) {
                Text(
                    text = "시작하기",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }
        }
    }
}