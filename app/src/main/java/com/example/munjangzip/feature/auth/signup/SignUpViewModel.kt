package com.example.munjangzip.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository
) : ViewModel() {
    private val _state = MutableStateFlow<SignUpState>(SignUpState.Nothing)
    val state = _state.asStateFlow()
    fun signUp(nickname: String, libraryName: String, email: String, password: String) {
        _state.value = SignUpState.Loading

        viewModelScope.launch {
            try {
                Log.d("SignUp", "회원가입 요청 시작")
                Log.d("SignUp", "요청 데이터: nickname=$nickname, libraryName=$libraryName, email=$email, password=******")

                val response = repository.registerUser(nickname, libraryName, email, password)

                Log.d("SignUp", "응답 수신: isSuccess=${response.isSuccess}, code=${response.code}, message=${response.message}")

                if (response.isSuccess) {
                    Log.d("SignUp", "회원가입 성공!")
                    _state.value = SignUpState.Success
                } else {
                    Log.e("SignUp", "회원가입 실패: ${response.message}")
                    _state.value = SignUpState.Error(response.message)
                }
            } catch (e: retrofit2.HttpException) {
                val errorBody = e.response()?.errorBody()?.string() ?: "Unknown error"
                Log.e("SignUp", "회원가입 요청 중 오류 발생 - HTTP ${e.code()}: $errorBody", e)
                _state.value = SignUpState.Error("HTTP ${e.code()} - $errorBody")
            } catch (e: Exception) {
                Log.e("SignUp", "회원가입 요청 중 알 수 없는 오류 발생", e)
                _state.value = SignUpState.Error("회원가입 요청 실패: ${e.message}")
            }
        }
    }


}

sealed class SignUpState {
    object Nothing : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}
