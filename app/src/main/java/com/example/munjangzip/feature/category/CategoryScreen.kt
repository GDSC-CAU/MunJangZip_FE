/*###################
## 카테고리 보는 화면  ##
###################*/
package com.example.munjangzip.feature.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.ui.theme.BrightYellow
import androidx.compose.runtime.LaunchedEffect
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.collectAsState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.munjangzip.R
import com.example.munjangzip.feature.category.GetCategoryApi
import com.example.munjangzip.ui.BackGround

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController, viewModel: CategoryViewModel = hiltViewModel()) {
    val categoryState by viewModel.categoryState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
                shadowElevation = 8.dp
            ) {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BrightYellow
                    ),
                    title = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "${categoryState?.result?.nickName ?: "사용자"} 님의",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "'${categoryState?.result?.libraryName ?: ""}' 도서관",
                                fontSize = 20.sp,
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold,
                            )
                        }
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
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Box(contentAlignment = Alignment.Center) {
                ElevatedButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(65.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightYellow,
                        contentColor = Color.Gray
                    ),
                    onClick = { navController.navigate("addCategory") },
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "카테고리 추가하기",
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    )
                }
                Image(
                    painter = painterResource(R.drawable.fish),
                    contentDescription = null,
                    modifier = Modifier
                        .zIndex(1f)
                        .offset(x = (-105).dp)
                        .size(100.dp)
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))

            //카테고리 리스트-> BookCategoryPager에 전달
            categoryState?.result?.categoryList?.let { categories ->
                BookCategoryPager(
                    navController = navController,
                    categories = categories,
                    refreshCallback = { viewModel.fetchCategories()}// ✅ API 재요청 추가
                )
            }
            Spacer(modifier = Modifier.padding(13.dp))
        }
    }
}
