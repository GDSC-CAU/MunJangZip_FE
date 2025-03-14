package com.example.munjangzip.feature.createMemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.ui.BackGroundBubble
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

val LightYellow = Color(0xFFFFF2D3)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMemo(
    navController: NavController,
    bookId: Int // 책 ID 전달
) {
    var selectedColor by remember { mutableStateOf(Color(0xFFFFF2D3)) }  // base 색상
    var memoText by remember { mutableStateOf("") }
    val viewModel: CreateMemoViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    // selectedColor 에 따라서 배경 이미지 바꾸기(절대 변경하지 않음)
    val memoBackground = when (selectedColor) {
        Color(0xFFFFF2D3) -> R.drawable.memo_background1  // 노란색
        Color(0xFFFDEDED) -> R.drawable.memo_background2  // 분홍색
        Color(0xFFE0E0E0) -> R.drawable.memo_background3  // 회색
        Color(0xFFE5EFFD) -> R.drawable.memo_background4  // 하늘색
        Color(0xFFE2F5E2) -> R.drawable.memo_background5  // 연두색
        else -> R.drawable.memo_background1  // 기본 색상
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWidget(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // 뒤 배경
            BackGroundBubble(R.drawable.bubble2)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // "바로 글쓰기" 표시부분
                Box(contentAlignment = Alignment.Center) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = LightYellow,
                        modifier = Modifier
                            .padding(8.dp)
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp))
                    ) {
                        Text(
                            text = "바로 글쓰기",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier.padding(vertical = 15.dp, horizontal = 40.dp)
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.fish),
                        contentDescription = "붕어빵 아이콘",
                        modifier = Modifier
                            .offset(x = (-90).dp)
                            .size(100.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                // 메모 입력 창
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.95f)
                        .height(200.dp)
                ) {
                    // 메모 배경
                    Image(
                        painter = painterResource(memoBackground),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(30.dp))
                    )
                    // 메모 입력
                    TextField(
                        value = memoText,
                        onValueChange = { memoText = it },
                        placeholder = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("눌러서 입력하기", color = Color.Gray)
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)  // 내부 패딩
                            .background(Color.Transparent),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.Gray
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            cursorColor = Color.Gray,
                            focusedTextColor = Color.Gray,
                            unfocusedTextColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

                // 색상 선택 팔레트
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val colors = listOf(
                        Color(0xFFFFF2D3),  // 노란색
                        Color(0xFFFDEDED),  // 분홍색
                        Color(0xFFE0E0E0),  // 회색
                        Color(0xFFE5EFFD),  // 하늘색
                        Color(0xFFE2F5E2)   // 연두색
                    )
                    colors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .padding(10.dp)
                                .background(color, CircleShape)
                                .clickable { selectedColor = color }
                                .border(
                                    width = if (selectedColor == color) 3.dp else 0.dp,
                                    color = if (selectedColor == color) Color.Gray else Color.Transparent,
                                    shape = CircleShape
                                )
                        )
                    }
                }
                // 저장 버튼
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.saveMemo(
                                bookId = bookId,
                                content = memoText,
                                selectedColor = selectedColor,
                                navController = navController
                                    // popBackStack
                            )
                        }
                    },
                    modifier = Modifier
                        .width(130.dp)
                        .height(50.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.98f)
                    )
                ) {
                    Text(
                        text = "저장하기",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


@HiltViewModel
class CreateMemoViewModel @Inject constructor(
    private val memoApi: MemoApiService
) : ViewModel() {

    fun saveMemo(
        bookId: Int,
        content: String,
        selectedColor: Color,
        navController: NavController // 네비게이션 컨트롤러 추가
    ) {
        val colorCode = when (selectedColor) {
            Color(0xFFFFF2D3) -> 1
            Color(0xFFFDEDED) -> 2
            Color(0xFFE0E0E0) -> 3
            Color(0xFFE5EFFD) -> 4
            Color(0xFFE2F5E2) -> 5
            else -> 1
        }

        val request = MemoRequest(
            bookId = bookId,
            paragraph = Paragraph(
                content = content,
                imageURL = null,  // 이미지 URL 없음
                color = colorCode
            )
        )

        viewModelScope.launch {
            val response = memoApi.createMemo(request)
            if (response.isSuccess) {
                //성공하면 이전 화면으로 돌아가기
                navController.popBackStack()
            } else {
                // 실패 시 처리
            }
        }
    }
}
