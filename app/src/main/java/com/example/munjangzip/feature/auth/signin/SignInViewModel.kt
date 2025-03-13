package com.example.munjangzip.feature.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.munjangzip.data.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: SignInRepository,
    val userPreferences: UserPreferences  // ✅ UserPreferences 주입

) : ViewModel() {

    private val _state = MutableStateFlow<SignInState>(SignInState.Nothing)
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String) {
        _state.value = SignInState.Loading

        viewModelScope.launch {
            try {
                val response = repository.signIn(email, password)
                if (response.isSuccess && response.result != null) {
                    userPreferences.saveTokens(
                        response.result?.accessToken ?: "",
                        response.result?.refreshToken ?: ""
                    )
                    _state.value = SignInState.Success
                } else {
                    _state.value = SignInState.Error("로그인 실패: ${response.message}")
                }

            } catch (e: Exception) {
                _state.value = SignInState.Error("네트워크 오류: ${e.message}")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            userPreferences.clearTokens() // 저장된 토큰 삭제
            _state.value = SignInState.Nothing // 상태 초기화
        }

    }

}

// 상태 클래스 수정 (Success를 객체로 선언)
sealed class SignInState {
    object Nothing : SignInState()
    object Loading : SignInState()
    object Success : SignInState()
    data class Error(val message: String) : SignInState()
}

