plugins {
    id("com.picsum.kotlin.android")
    id("com.picsum.android.library")
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.screens"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(libs.circuit.runtime)
}
