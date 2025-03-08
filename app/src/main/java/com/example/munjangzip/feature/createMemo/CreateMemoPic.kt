package com.example.munjangzip.feature.createMemo

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.ui.BackGroundBubble
import coil.compose.rememberAsyncImagePainter
import java.io.OutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMemoPic(navController: NavController) {
    // 사진 Uri를 저장할 상태 변수
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // 사진 찍기 위한 런처 설정
    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (!success) {
            imageUri = null  // 실패 시 Uri 초기화
        }
    }

    // 임시 저장소에서 Uri 생성
    fun createImageUri(context: Context): Uri? {
        val contentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "new_image.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackGroundBubble(R.drawable.bubble4)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(50.dp))

                // 사진 선택하기 버튼
                Button(
                    onClick = { /* 사진 선택 로직 */ },
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(30.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE5EFFD)
                    )
                ) {
                    Text(
                        text = "사진 선택",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                // 사진 찍기 버튼
                Button(
                    onClick = {
                        val context = navController.context
                        val uri = createImageUri(context)  // 임시 저장소에 Uri 생성
                        uri?.let {
                            imageUri = it  // Uri 저장
                            takePictureLauncher.launch(it)  // 카메라 실행
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(30.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFF2D3)
                    )
                ) {
                    Text(
                        text = "사진 찍기",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                // 찍은 사진 보여주기
                imageUri?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "찍은 사진",
                        modifier = Modifier
                            .size(300.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .shadow(8.dp, shape = RoundedCornerShape(20.dp))
                    )
                }
            }
        }
    }
}
