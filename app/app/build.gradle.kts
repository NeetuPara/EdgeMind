plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.application)
    kotlin("kapt")
}

android {
    namespace = "com.edgemind.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.edgemind.app"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("edgemind-release.jks")
            storePassword = "edgemind123"
            keyAlias = "edgemind"
            keyPassword = "edgemind123"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material.icon.extended)
    implementation(libs.androidx.compose.navigation)
    debugImplementation(libs.androidx.ui.tooling)

    // Hilt DI
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    // LiteRT LM — On-device LLM inference
    implementation(libs.litertlm)

    // Room — Persistent chat history
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // WorkManager — Background model downloads
    implementation(libs.androidx.work.runtime)

    // DataStore — Settings persistence
    implementation(libs.androidx.datastore.preferences)

    // JSON parsing
    implementation(libs.com.google.code.gson)
}

kapt {
    correctErrorTypes = true
}
