package com.example.munjangzip.feature.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munjangzip.data.UserPreferences
import com.example.munjangzip.feature.category.GetCategoryRepository
import com.example.munjangzip.feature.category.GetCategoryResponse
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
}
