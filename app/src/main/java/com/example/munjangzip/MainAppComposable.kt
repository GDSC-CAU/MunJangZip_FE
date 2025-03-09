package com.example.munjangzip

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.munjangzip.feature.booklist.BookListScreen
import com.example.munjangzip.feature.category.CategoryScreen
import com.example.munjangzip.feature.savebook.TakePhotoPage
import com.example.munjangzip.feature.addCategory.AddCategoryScreen
import com.example.munjangzip.feature.auth.StartScreen
import com.example.munjangzip.feature.auth.signin.SignInScreen
import com.example.munjangzip.feature.auth.signup.SignUpScreen
import com.example.munjangzip.feature.booklist.BookListGrid
import com.example.munjangzip.feature.books.BookDetailScreen
import com.example.munjangzip.feature.loadBookInfo.LoadBookInfoScreen
import com.example.munjangzip.feature.loadBookInfo.NoBookInfoScreen
import com.example.munjangzip.feature.createMemo.CreateMemo
import com.example.munjangzip.feature.selectMemo.SelectMemo
import com.example.munjangzip.feature.createMemo.CreateMemoPic

@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "start") {

            //로그인 할지 회원가입할지 선택하는 화면
            composable(route = "start") {
                StartScreen(navController = navController)
            }

            //로그인
            composable(route = "login") {
                SignInScreen(navController = navController)
            }

            //회원가입
            composable(route = "register") {
                SignUpScreen(navController = navController)
            }

            //카테고리 페이지 (카테고리 목록들이 뜨는 페이지)
            composable(route = "category") {
                CategoryScreen(navController = navController)
            }

            composable(route = "booklist") {
                BookListScreen(navController = navController)
            }

            composable(route = "takephoto") {
                TakePhotoPage(navController = navController)
            }

            // 카테고리 추가 네비게이션
            composable(route = "addcategory") {
                AddCategoryScreen(navController = navController)
            }

            // 책 상세 페이지 네비게이션
            composable(route = "bookDetail/{bookId}") { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull() ?: 0
                BookDetailScreen(navController = navController, bookId = bookId)
            }

            //바코드로 찍은 책을 불러와서 등록하는 페이지 네비게이션
            composable(route = "bookInfo") {
                LoadBookInfoScreen(navController = navController)
            }

            //바코드로 인식한 책의 정보를 불러올 수 없을 때 네비게이션
            composable(route = "noBookInfo") {
                NoBookInfoScreen(navController = navController)
            }


            //메모 생성 페이지 네비게이션
            composable(route = "createMemo") {
                CreateMemo(navController = navController)
            }
            //메모 생성 선택 페이지 네비게이션
            composable(route = "selectMemo") {
                SelectMemo(navController = navController)
            }
            composable("createMemoPic") {
                CreateMemoPic(navController = navController)
            }


        }

    }
}
