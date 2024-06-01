plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.example.kitsyapihilt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kitsyapihilt"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures.viewBinding = true
}

dependencies {
    //UIComponents
    implementation(libs.bundles.uiComponents)
    testImplementation(libs.uiComponents.junit)
    androidTestImplementation(libs.uiComponents.androidTestJunit)
    androidTestImplementation(libs.uiComponents.androidTestEspresso)
    // Navigation component
    implementation(libs.bundles.navigationComponent)
    // Room
    implementation(libs.bundles.room)
    kapt(libs.room.roomCompiler)
    // Lifecycle
    implementation(libs.bundles.lifecycle)
    // Coroutines
    implementation(libs.bundles.coroutines)
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Paging
    implementation(libs.paging.pagingRuntime)
    // Glide
    implementation(libs.glide.glide)
    kapt(libs.glide.glideCompiler)
    // ViewBindingDelegate
    implementation(libs.viewBindingDelegate.viewBinding)
    // Retrofit
    implementation(libs.bundles.retrofit)
    // okHttp
    implementation(libs.bundles.okHttp)
    // Implementation project
    implementation(project(":domain"))
    implementation(project(":data"))
}