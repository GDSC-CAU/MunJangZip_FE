package com.example.munjangzip.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munjangzip.data.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: GetCategoryRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _categoryState = MutableStateFlow<GetCategoryResponse?>(null)
    val categoryState: StateFlow<GetCategoryResponse?> = _categoryState.asStateFlow()

    init {
        fetchCategories()
    }

    public fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                _categoryState.value = response
            } catch (e: Exception) {
                // 네트워크 오류 처리
                _categoryState.value = null
            }
        }
    }
}
