import java.util.Properties

plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")

    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.cinesphere"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cinesphere"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "TMDB_API_TOKEN", "\"${properties.getProperty("TMDB_API_TOKEN")}\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val hilt_version = "2.51.1"
    val nav_version = "2.7.7"
    val compose_nav_version = "1.6.6"
    val paging_version = "3.2.1"
    val sandwich_version = "2.0.7"

    implementation(project(":core:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //compose-navigation
    implementation("androidx.compose.material:material:${compose_nav_version}")

    //navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation(libs.androidx.navigation.runtime.ktx)

    //retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("io.coil-kt:coil-compose:2.6.0")

    //dagger-hilt
    implementation("com.google.dagger:hilt-android:${hilt_version}")
    kapt("com.google.dagger:hilt-android-compiler:${hilt_version}")

    //viewmodel
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //paging
    implementation("androidx.paging:paging-runtime:${paging_version}")
    // optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:3.3.0-beta01")

    //sandwich
    implementation("com.github.skydoves:sandwich:${sandwich_version}")
    implementation("com.github.skydoves:sandwich-retrofit:${sandwich_version}")

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}
