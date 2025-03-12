package com.example.munjangzip.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val accessToken: Flow<String?> = flow {
        emit(prefs.getString("ACCESS_TOKEN", null))
    }

    val refreshToken: Flow<String?> = flow {
        emit(prefs.getString("REFRESH_TOKEN", null))
    }

    // accessToken & refreshToken 저장 메서드 추가
    fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit()
            .putString("ACCESS_TOKEN", accessToken)
            .putString("REFRESH_TOKEN", refreshToken)
            .apply()
    }

    // 로그아웃 시 토큰 삭제 메서드 추가
    fun clearTokens() {
        prefs.edit()
            .remove("ACCESS_TOKEN")
            .remove("REFRESH_TOKEN")
            .apply()
    }
}
