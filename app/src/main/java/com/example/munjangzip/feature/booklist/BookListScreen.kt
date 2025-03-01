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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.BackGround
import com.example.munjangzip.ui.theme.BrightYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 32.dp),
                shadowElevation = 8.dp
                //color =
            )
            {
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
                            IconButton(onClick = {navController.popBackStack()}) {
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
                                    "수정 님의" , fontSize = 12.sp
                                )
                                Text(
                                    " '말랑말랑' 도서관"
                                )
                            }
                        }


                    },
                    /*
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(R.drawable.chevron_left),
                                contentDescription = null,
                            )
                        }
                    }*/


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
            BookListGrid(navController)
        }
    }
}
