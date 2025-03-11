package com.example.munjangzip.feature.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: SignInRepository
) : ViewModel() {
    private val _state = MutableStateFlow<SignInState>(SignInState.Nothing)
    val state = _state.asStateFlow()
    fun signIn(email: String, password: String) {
        _state.value = SignInState.Loading

        viewModelScope.launch {
            try {
                Log.d("SignIn", "로그인 요청 시작: email=$email, password=******")

                val response = repository.login(email, password)

                Log.d("SignIn", "응답 수신: isSuccess=${response.isSuccess}, code=${response.code}, message=${response.message}")

                if (response.isSuccess && response.result != null) {
                    Log.d("SignIn", "로그인 성공! AccessToken=${response.result.accessToken}")

                    _state.value = SignInState.Success(response.result.accessToken, response.result.memberId)
                } else {
                    Log.e("SignIn", "로그인 실패: ${response.message}")
                    _state.value = SignInState.Error(response.message)
                }
            } catch (e: retrofit2.HttpException) {
                val errorBody = e.response()?.errorBody()?.string() ?: "Unknown error"
                Log.e("SignIn", "로그인 요청 중 오류 발생 - HTTP ${e.code()}: $errorBody", e)
                _state.value = SignInState.Error("HTTP ${e.code()} - $errorBody")
            } catch (e: Exception) {
                Log.e("SignIn", "로그인 요청 중 알 수 없는 오류 발생", e)
                _state.value = SignInState.Error("로그인 요청 실패: ${e.message}")
            }
        }
    }

}

sealed class SignInState {
    object Nothing : SignInState()
    object Loading : SignInState()
    data class Success(val accessToken: String, val memberId: Int) : SignInState()
    data class Error(val message: String) : SignInState()
}
