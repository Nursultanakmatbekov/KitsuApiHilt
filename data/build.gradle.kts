plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // UI
    implementation(libs.uiComponents.androidCore)
    testImplementation(libs.uiComponents.junit)
    androidTestImplementation(libs.uiComponents.androidTestJunit)
    // Paging
    implementation(libs.paging.pagingRuntime)
    // Retrofit
    implementation(libs.bundles.retrofit)
    // okHttp
    implementation(libs.bundles.okHttp)
    // Room
    implementation(libs.bundles.room)
    kapt(libs.room.roomCompiler)
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Coroutines
    implementation(libs.bundles.coroutines)
    // Impl project
    implementation(project(":domain"))

}