package com.example.munjangzip.appbar
//TopAppBar 코드
//AddCategoryScreen, TakePhotoScreen 등등..

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.munjangzip.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarWidget (navController: NavController) {

    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { // 뒤로 가기 버튼
                Icon(
                    painter = painterResource(R.drawable.chevron_left),
                    contentDescription = "뒤로가기"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent) // TopBar 투명
    )
}
