// 말풍선버전 배경
package com.example.munjangzip.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.munjangzip.R

@Composable
fun BackGroundBubble(drawing: Int) { //말품선으로 입력하고 싶은 이미지 인수로 전달

    //var drawing = R.drawable.bubble

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            alpha = 0.3F
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)

            ) {
                // 말풍선 (고양이 뒤쪽에 배치)
                Image(
                    painter = painterResource(drawing), //
                    contentDescription = "말풍선",
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.TopStart) // 말풍선 고양이 위쪽 정렬
                        .offset(x = (-100).dp, y = (-90).dp)
                )
                Image(
                    painter = painterResource(R.drawable.cat),
                    contentDescription = "고양이",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}
