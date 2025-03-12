package com.example.munjangzip.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: GetCategoryRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow<GetCategoryResponse?>(null)
    val categoryState: StateFlow<GetCategoryResponse?> = _categoryState

    fun fetchCategories() {
        viewModelScope.launch {
            val response = repository.getCategories()
            _categoryState.value = response
        }
    }
}
