package com.example.munjangzip.feature.booklist

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munjangzip.data.UserPreferences
import com.example.munjangzip.feature.category.GetCategoryRepository
import com.example.munjangzip.feature.category.GetCategoryResponse
import com.example.munjangzip.feature.savebook.GetBookRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repository: GetCategoryRepository,
    private val bookRepository: BookRepository,

    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _userState = MutableStateFlow<GetCategoryResponse?>(null)
    val userState: StateFlow<GetCategoryResponse?> = _userState.asStateFlow()

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                _userState.value = response
            } catch (e: Exception) {
                _userState.value = null
            }
        }
    }


    //private val _bookListState = MutableStateFlow<BookResponse<BookListResult>>(null)

    private val _bookListState = MutableStateFlow(
        BookResponse( // ✅ 기본값 설정
            isSuccess = false,
            code = "",
            message = "",
            result = BookListResult(
                categoryId = 0,
                categoryName = "",
                memberId = 0,
                books = emptyList()
            )
        )
    )
    val bookListState: StateFlow<BookResponse<BookListResult>> = _bookListState.asStateFlow()

    fun fetchBookList(categoryId: Int) {
        viewModelScope.launch {
            Log.d("BookListViewModel", "fetchBookList() called with categoryId: $categoryId")

            try {
                val response = bookRepository.checkBook(categoryId)
                if (response != null) {
                    Log.d("BookListViewModel", "Response received: $response") // ✅ 응답 확인 로그
                    _bookListState.value = response
                }
                else{
                    Log.e("BookListViewModel", "Response is null")

                }
            } catch (e: Exception) {
                //_bookListState.value = null //null 발생시 기본값 유지
                Log.e("BookListViewModel", "Error fetching book list: ${e.message}")

            }
        }
    }

    private val _bookInfoState = MutableStateFlow(
        BookResponse( // ✅ 기본값 설정
            isSuccess = false,
            code = "",
            message = "",
            result = BookResult(
                memberId = 0,
                bookId = 0,
                title = "",
                author = "",
                registerAt = "",
                category = "",
                coverImageUrl = "",
                isbn = "")
        )
    )
    val bookInfoState: StateFlow<BookResponse<BookResult>> = _bookInfoState.asStateFlow()


    fun fetchBookInfo(categoryId: Int, title: String, author: String, category: String, coverImageUrl: String, isbn: String) {
        viewModelScope.launch {

            val response = bookRepository.registerBook(categoryId, title, author, category, coverImageUrl, isbn)

            if (response != null) {
                _bookInfoState.value = response
            }

        }

    }


}
