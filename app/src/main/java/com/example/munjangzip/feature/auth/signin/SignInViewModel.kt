// 로그인 관련 데이터관리 view

package com.example.munjangzip.feature.auth.signin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow<SignInState>(SignInState.Nothing)
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String) {
        _state.value = SignInState.Loading
//백엔드 연동하는 부분으로 채워야함
//                if (task.isSuccessful) {
//                    _state.value = SignInState.Success
//                } else {
//                    _state.value = SignInState.Error
//                }

    }
}
sealed class SignInState {
    object Nothing: SignInState()
    object Loading: SignInState()
    object Success: SignInState()
    object Error: SignInState()

}