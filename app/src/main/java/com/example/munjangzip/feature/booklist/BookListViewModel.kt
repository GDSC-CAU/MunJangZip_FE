package com.example.munjangzip.feature.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munjangzip.data.UserPreferences
import com.example.munjangzip.feature.category.GetCategoryRepository
import com.example.munjangzip.feature.category.GetCategoryResponse
import com.example.munjangzip.feature.booklist.GetBookListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val repository: GetCategoryRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _userState = MutableStateFlow<GetCategoryResponse?>(null)
    val userState: StateFlow<GetCategoryResponse?> = _userState.asStateFlow()

    private val _bookListState = MutableStateFlow<GetBookListResponse?>(null)
    val bookListState: StateFlow<GetBookListResponse?> = _bookListState.asStateFlow()

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

    fun fetchBooksByCategory(categoryId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getBooksByCategory(categoryId)
                _bookListState.value = response
            } catch (e: Exception) {
                _bookListState.value = null
            }
        }
    }
}
