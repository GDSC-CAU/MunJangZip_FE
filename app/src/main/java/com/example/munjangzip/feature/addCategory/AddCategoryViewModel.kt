package com.example.munjangzip.feature.addCategory
// ViewModel에서 사용자 입력을 받아 API 요청을 실행

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AddCategoryState>(AddCategoryState.Idle)
    val state = _state.asStateFlow()

    fun addCategory(categoryName: String) {
        _state.value = AddCategoryState.Loading

        viewModelScope.launch {
            try {
                val response = repository.addCategory(categoryName)
                if (response.isSuccess) {
                    _state.value = AddCategoryState.Success(response.result.categoryName)
                } else {
                    _state.value = AddCategoryState.Error(response.message)
                }
            } catch (e: Exception) {
                _state.value = AddCategoryState.Error("카테고리 추가 실패: ${e.message}")
            }
        }
    }
}

sealed class AddCategoryState {
    object Idle : AddCategoryState()
    object Loading : AddCategoryState()
    data class Success(val categoryName: String) : AddCategoryState()
    data class Error(val message: String) : AddCategoryState()
}

