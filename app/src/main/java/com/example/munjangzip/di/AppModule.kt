package com.example.munjangzip.di

import com.example.munjangzip.feature.auth.signup.SignUpApi
import com.example.munjangzip.feature.auth.signup.SignUpRepository
import com.example.munjangzip.feature.auth.signin.SignInApi
import com.example.munjangzip.feature.auth.signin.SignInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // 요청,응답 바디까지 출력
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // HTTP 요청 로그
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://햐햐히히히히/") //백엔드 주소
            .client(client) // 로깅 인터셉터 적용
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    //회원가입
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

    //로그인
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
}
