package com.example.munjangzip.feature.createMemo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget  // 🔥 TopBarWidget 임포트

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMemoPic(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {  // 🔥 TopBar 추가
            TopBarWidget(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(20.dp))

                Text(
                    text = "사진 추가하기",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(20.dp)
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = { /* 사진 추가 기능 */ },
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE5EFFD)
                    )
                ) {
                    Text(
                        text = "사진 선택",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}
