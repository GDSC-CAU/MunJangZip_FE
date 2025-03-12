package com.example.munjangzip.feature.loadBookInfo

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.feature.savebook.GetBookViewModel
import com.example.munjangzip.feature.savebook.startMLKitScanner
import com.example.munjangzip.ui.BackGroundBubble
import com.example.munjangzip.ui.theme.Gray10

@Composable
fun NoBookInfoScreen(navController: NavController, viewModel: GetBookViewModel = hiltViewModel()) {
    val context = LocalContext.current
//    var isScanning by remember { mutableStateOf(false) }  // 바코드 스캔 여부
//    var isLoading by remember { mutableStateOf(false) }   // API 로딩 상태

    val loadBookState by viewModel.bookState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(),) {
            BackGroundBubble(R.drawable.nobookbubble)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(8.dp))

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 250. dp, height = 420.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(R.drawable.nobook),
                                contentDescription = "Loaded Book",
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 120.dp, start = 20.dp, end = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "도서관에 책이\n없나봐요!!",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    textAlign = TextAlign.Center

                                )
                            }
                        }

                    }


                Spacer(modifier = Modifier.padding(8.dp))

                ElevatedButton(
                    onClick = {
//                        (context as? Activity)?.startMLKitScanner { scannedIsbn ->
//                            isScanning = false  // ✅ 스캔 완료
//                            isLoading = true    // ✅ 데이터 로딩 시작
//                            Log.d("TakePhotoPage", "바코드 스캔 완료: $scannedIsbn")
//
//                            // ISBN이 정상적으로 스캔되면 ViewModel에서 API 호출
//                            viewModel.fetchBooks(scannedIsbn)
//                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Gray10
                    )
                ) {
                    Text(
                        text = "다시찍기",
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

    // LaunchedEffect로 API 결과 확인 후 화면 이동
//    LaunchedEffect(loadBookState) {
//        if (isLoading) {  // 🔥 바코드를 스캔하고 API 요청 후에만 실행되도록 변경
//            loadBookState?.result?.title?.let {
//                Log.d("TakePhotoPage", "책 정보 로드 성공: $it")
//                isLoading = false
//                navController.navigate("bookInfo")
//            } ?: run {
//                Log.e("TakePhotoPage", "책 정보 불러오기 실패: 응답이 null")
//                isLoading = false
//                navController.navigate("noBookInfo")
//            }
//        }
//    }

}