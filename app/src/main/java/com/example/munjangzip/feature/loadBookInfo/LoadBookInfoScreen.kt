package com.example.munjangzip.feature.loadBookInfo

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.feature.savebook.GetBookViewModel
import com.example.munjangzip.feature.savebook.startMLKitScanner
import com.example.munjangzip.ui.BackGroundBubble
import com.example.munjangzip.ui.theme.Gray10

@Composable
fun LoadBookInfoScreen(navController: NavController, viewModel: GetBookViewModel = hiltViewModel()) {
    val loadBookState by viewModel.bookState.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }


    // savedStateHandle에서 ISBN 가져오기
    val isbn = navController.previousBackStackEntry?.savedStateHandle?.get<String>("isbn_key") ?: ""

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

    ) {

        Box(modifier = Modifier.fillMaxSize(),) {
            BackGroundBubble(R.drawable.getbookbubble)
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
                    if (loadBookState != null && loadBookState?.isSuccess == true) {
                        // Using Coil to load image from URL
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(loadBookState?.result?.coverImageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Book Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            //error = painterResource(id = R.drawable.book2) // Fallback if loading fails
                        )
                        Log.e("TakePhotoPage", "책 정보 불러오기 성공")
                    } else {
                        // Show placeholder while loading or if no data
                        Image(
                            painter = painterResource(R.drawable.book2),
                            contentDescription = "Placeholder Book",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Log.e("LoadBookScreen", "책 사진 불러오기 실패: URL=${loadBookState?.result?.coverImageUrl},title=${loadBookState?.result?.title}, isSuccess=${loadBookState?.isSuccess}")

                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))

                ElevatedButton(
                    onClick = { //등록
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Gray10
                    )
                ) {
                    Text(
                        text = "등록하기",
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
}