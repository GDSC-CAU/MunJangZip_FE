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
import com.example.munjangzip.feature.booklist.BookApi
import com.example.munjangzip.feature.booklist.BookRepository
import com.example.munjangzip.feature.category.GetCategoryApi
import com.example.munjangzip.feature.category.GetCategoryRepository
import com.example.munjangzip.feature.savebook.BarCodeApi
import com.example.munjangzip.feature.savebook.GetBookRepository
import com.example.munjangzip.network.AuthApi

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // AuthApi 추가 (토큰 재발급용)
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }


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
            .baseUrl("ㅎㅎㅎ") // 백엔드 주소
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
    fun provideSignInRepository(
        api: SignInApi,
        userPreferences: UserPreferences
    ): SignInRepository {
        return SignInRepository(api, userPreferences)
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

    // 카테고리 조회 API
    @Provides
    @Singleton
    fun provideGetCategoryApi(retrofit: Retrofit): GetCategoryApi {
        return retrofit.create(GetCategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetCategoryRepository(api: GetCategoryApi, userPreferences: UserPreferences): GetCategoryRepository {
        return GetCategoryRepository(api, userPreferences)
    }

    //isbn 책 정보 조회 API
    @Provides
    @Singleton
    fun provideBarCodeAPI(retrofit: Retrofit): BarCodeApi {
        return retrofit.create(BarCodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetBookRepository(api: BarCodeApi, userPreferences: UserPreferences): GetBookRepository {
        return GetBookRepository(api, userPreferences)
    }

    // 북리스트 정보 조회 API
    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(api: BookApi, userPreferences: UserPreferences): BookRepository {
        return BookRepository(api, userPreferences)
    }


}