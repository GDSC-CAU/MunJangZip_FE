package com.example.munjangzip

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.munjangzip.data.UserPreferences
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
import com.example.munjangzip.feature.auth.signin.SignInViewModel
import com.example.munjangzip.feature.booklist.BookListViewModel

import com.example.munjangzip.feature.savebook.GetBookViewModel

@Composable
fun MainApp(userPreferences: UserPreferences) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()

        val viewModel: SignInViewModel = hiltViewModel()

        val bookViewModel: GetBookViewModel = hiltViewModel() //공유 뷰모델로 바코드 인식후 bookinfo페이지와 뷰모델 데이터 공유
        val bookInfoViewModel: BookListViewModel = hiltViewModel()


        // UserPreferences에서 저장된 accessToken 확인
        val accessToken by userPreferences.accessToken.collectAsState(initial = null)

        val start = if (accessToken != null && accessToken!!.isNotEmpty()) "category" else "start"// 로그인이 된 상태면 바로 카테고리 화면으로

        NavHost(navController = navController, startDestination = start) {

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

//            composable(route = "booklist/{categoryId}") { backStackEntry ->
//                val categoryId = backStackEntry.arguments?.getInt("categoryId")
//                if (categoryId != null) {
//                    BookListScreen(navController = navController, categoryId = categoryId)
//                }
//            }

            //카테고리별 북리스트 페이지
            composable(
                route = "booklist/{categoryId}",
                arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 1
                BookListScreen(navController = navController, categoryId = categoryId)
            }

//            composable(route = "takephoto/")
//            { backStackEntry ->
//                val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 1
//                TakePhotoPage(navController = navController, bookViewModel, categoryId = categoryId)
//            }
            composable(
                route = "takephoto/{categoryId}",
                arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 1
                TakePhotoPage(navController = navController, bookViewModel, categoryId = categoryId)

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
//            composable(route = "bookInfo/") { backStackEntry ->
//                val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 1
//                LoadBookInfoScreen(navController = navController, bookViewModel, categoryId = categoryId, bookInfoViewModel)
//            }

            composable(route = "bookInfo/{categoryId}") { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 1
                LoadBookInfoScreen(
                    navController = navController,
                    viewModel = bookViewModel,
                    categoryId = categoryId,
                    bookInfoViewModel = bookInfoViewModel
                )

            }


            //바코드로 인식한 책의 정보를 불러올 수 없을 때 네비게이션
            composable(route = "noBookInfo") {
                NoBookInfoScreen(navController = navController)
            }


            // 메모 생성 페이지 네비게이션 (bookId 포함)
            composable(
                route = "createMemo?bookId={bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.IntType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
                CreateMemo(navController = navController, bookId = bookId)
            }

// SelectMemo 페이지 네비게이션 (bookId 포함)
            composable(
                route = "selectMemo?bookId={bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.IntType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
                SelectMemo(navController = navController, bookId = bookId)
            }

            composable("createMemoPic") {
                CreateMemoPic(navController = navController)
            }


        }

    }
}
