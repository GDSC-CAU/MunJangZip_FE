plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")


}

android {
    namespace = "com.example.munjangzip"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.munjangzip"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.compose.material3:material3") //ff
    implementation("io.coil-kt:coil-compose:2.4.0")
    //hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation ("com.google.accompanist:accompanist-pager:0.30.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.0")


    implementation("androidx.activity:activity-compose:1.10.1")

    //바코드 스캔을 위한 ML KIT
    implementation (libs.barcode.scanning)

    implementation ("com.google.android.gms:play-services-code-scanner:16.1.0")


    // 레트로핏
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Hilt (의존성 주입하는거)
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Gson (JSON 파싱위한거 )
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // DataStore Preferences
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    //Kotlin 비동기 데이터 저장
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.13-rc")


}


//hilt
// Allow references to generated code
kapt {
    correctErrorTypes = true
}