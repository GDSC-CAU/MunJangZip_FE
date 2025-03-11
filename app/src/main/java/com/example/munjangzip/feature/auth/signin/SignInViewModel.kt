package com.example.munjangzip.feature.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.munjangzip.data.UserPreferences


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: SignInRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    private val _state = MutableStateFlow<SignInState>(SignInState.Nothing)
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String) {
        _state.value = SignInState.Loading

        viewModelScope.launch {
            try {
                val response = repository.login(email, password)

                if (response.isSuccess && response.result != null) {
                    val accessToken = response.result.accessToken
                    Log.d("SignIn", "로그인 성공! 토큰 저장: $accessToken")

                    // ✅ 토큰 저장
                    userPreferences.saveAccessToken(accessToken)

                    _state.value = SignInState.Success(accessToken, response.result.memberId)
                } else {
                    _state.value = SignInState.Error(response.message)
                }
            } catch (e: Exception) {
                _state.value = SignInState.Error("로그인 실패: ${e.message}")
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
