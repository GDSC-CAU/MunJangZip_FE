package com.example.munjangzip.feature.savebook

import android.util.Log
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
class GetBookViewModel @Inject constructor(
    private val repository: GetBookRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _bookState = MutableStateFlow<GetBookResponse?>(null)
    val bookState: StateFlow<GetBookResponse?> = _bookState.asStateFlow()

//    init {
//        fetchBooks()
//    }

    fun fetchBooks(isbn: String) {
        viewModelScope.launch {
            try {
                val response = repository.getBookInfo(isbn)
                _bookState.value = response
                if (response != null) {
                    Log.d("GetBookViewModel", "책 정보 불러오기 성공: ${response.result.title}")
                } else {
                    Log.e("GetBookViewModel", "책 정보 불러오기 실패: 응답이 null")
                }
            } catch (e: Exception) {
                // 네트워크 오류 처리
                _bookState.value = null
                Log.e("GetBookRepository", "API 요청 중 오류 발생: ${e.message}")

            }
        }
    }
}
