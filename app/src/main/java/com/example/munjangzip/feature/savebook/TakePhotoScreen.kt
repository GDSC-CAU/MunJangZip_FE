package com.example.munjangzip.feature.savebook

import android.app.Activity
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.theme.Gray10
import com.example.munjangzip.ui.theme.Ivory

@Composable
fun TakePhotoPage(navController: NavController, viewModel: GetBookViewModel = hiltViewModel(), categoryId: Int) {

    val context = LocalContext.current
    var isScanning by remember { mutableStateOf(false) }  // 바코드 스캔 여부
    var isLoading by remember { mutableStateOf(false) }   // API 로딩 상태

    var stringIsbn: String = null.toString() //isbn 저장
    val loadBookState by viewModel.bookState.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("TakePhotoPage", "전달된 categoryId: $categoryId")
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController)
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
                onClick = {
                    (context as? Activity)?.startMLKitScanner { scannedIsbn ->
                        isScanning = false  //  스캔 완료
                        isLoading = true    //  데이터 로딩 시작
                        Log.d("TakePhotoPage", "바코드 스캔 완료: $scannedIsbn")

                        // ISBN이 정상적으로 스캔되면 ViewModel에서 API 호출
                        viewModel.fetchBooks(scannedIsbn)
                        stringIsbn = scannedIsbn
                    }
                },

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

    // LaunchedEffect로 API 결과 확인 후 화면 이동
    LaunchedEffect(loadBookState) {
        if (isLoading) {  // 🔥 바코드를 스캔하고 API 요청 후에만 실행되도록 변경
            loadBookState?.result?.title?.let {
                Log.d("TakePhotoPage", "책 정보 로드 성공: $it")
                isLoading = false
                //navController.currentBackStackEntry?.savedStateHandle?.set("isbn_key", stringIsbn )
                //navController.navigate("bookInfo")
                navController.navigate("bookInfo/${categoryId}")



            } ?: run {
                Log.e("TakePhotoPage", "책 정보 불러오기 실패: 응답이 null")
                isLoading = false
                navController.navigate("noBookInfo")
            }
        }
    }
}