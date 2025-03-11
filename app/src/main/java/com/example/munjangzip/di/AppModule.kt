package com.example.munjangzip.di

import android.content.Context
import com.example.munjangzip.data.UserPreferences
import com.example.munjangzip.feature.addCategory.CategoryApi
import com.example.munjangzip.network.AuthInterceptor
import com.example.munjangzip.feature.auth.signup.SignUpApi
import com.example.munjangzip.feature.auth.signup.SignUpRepository
import com.example.munjangzip.feature.auth.signin.SignInApi
import com.example.munjangzip.feature.auth.signin.SignInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.munjangzip.feature.addCategory.CategoryRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //UserPreferences Hilt에 제공
    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    //인증 헤더 자동 추가 인터셉터
    @Provides
    @Singleton
    fun provideAuthInterceptor(userPreferences: UserPreferences): AuthInterceptor {
        return AuthInterceptor(userPreferences)
    }

    //HTTP 로그 인터셉터
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // 요청, 응답 바디까지 출력
        }
    }



    //OkHttpClient 설정 (authInterceptor 추가)
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // 모든 요청에 토큰 자동 추가
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Retrofit 설정
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://아 귀찮아 진짜이거ㅎㅎ/") // 백엔드 주소
            .client(client) // OkHttpClient 적용
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 회원가입 API
    @Provides
    @Singleton
    fun provideSignUpApi(retrofit: Retrofit): SignUpApi {
        return retrofit.create(SignUpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(api: SignUpApi): SignUpRepository {
        return SignUpRepository(api)
    }

    // 로그인 API
    @Provides
    @Singleton
    fun provideSignInApi(retrofit: Retrofit): SignInApi {
        return retrofit.create(SignInApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSignInRepository(api: SignInApi): SignInRepository {
        return SignInRepository(api)
    }

    // 카테고리 추가 API
    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(api: CategoryApi, userPreferences: UserPreferences): CategoryRepository {
        return CategoryRepository(api, userPreferences)
    }


}
