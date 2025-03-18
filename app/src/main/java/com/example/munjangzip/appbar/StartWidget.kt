package com.example.munjangzip.appbar
/*
StartScreen에서 사용할 버튼
 */
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.theme.BrightYellow


@Composable
fun StartWidget(navController: NavController, textLabel: String, buttonColor: Color, fishImage :Int, navigationPage: String) {
    Box(
        contentAlignment = Alignment.Center,
    ){
        // 버튼 (뒤쪽 - 낮은 zIndex)
        ElevatedButton(
            modifier = Modifier
                .padding(16.dp)
                .height(65.dp)
                .width(140.dp),
            //.zIndex(0f), // 버튼의 zIndex를 낮게 설정

            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.Gray,
                disabledContainerColor = buttonColor,
                disabledContentColor = Color.White
            ),
            onClick = {navController.navigate(navigationPage)},//카테고리 추가 페이지로 이동
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = textLabel,
                modifier = Modifier.padding(start = 10.dp),//왼쪽 패딩 (붕어랑 안겹치게)
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            )
        }

        // 이미지 (앞쪽 - 높은 zIndex)
        Image(
            painter = painterResource(fishImage),
            contentDescription = null,
            modifier = Modifier
                .zIndex(1f)// 이미지의 zIndex를 높게 설정
                .offset(x = (-75).dp)
                .size(100.dp)
        )
    }

}
