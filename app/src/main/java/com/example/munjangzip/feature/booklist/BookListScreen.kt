package com.example.munjangzip.feature.booklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.theme.BrightYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavController, viewModel: BookListViewModel = hiltViewModel(), categoryId: Int) {

    val userState by viewModel.userState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 32.dp),
                shadowElevation = 8.dp
            ) {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BrightYellow
                    ),
                    title = {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Image(
                                    painter = painterResource(R.drawable.chevron_left),
                                    contentDescription = "뒤로 가기",
                                )
                            }
                            Spacer(modifier = Modifier.padding(12.dp))
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "${userState?.result?.nickName ?: "사용자"} 님의",
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "'${userState?.result?.libraryName?: ""}' 도서관",
                                    fontSize = 20.sp,
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
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
            BookListGrid(navController, viewModel, categoryId)
        }
    }
}
