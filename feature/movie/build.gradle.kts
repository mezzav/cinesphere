plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.movie"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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

    buildFeatures {
        compose  = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:domain"))
    implementation(project(":core:utils"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //paging and paging-compose
    implementation(libs.androidx.paging.runtime)
    implementation(libs.paging.compose)

    //coil
    implementation(libs.coil.compose)

    //dagger-hilt
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.hilt.navigation.compose)

    //compose navigation
    implementation(libs.androidx.navigation.compose)

    //sandwich
    implementation(libs.skydoves.sandwich)
    implementation(libs.skydoves.sandwich.retrofit)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}