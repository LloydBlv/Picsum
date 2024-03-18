plugins {
    id("com.picsum.android.application")
    id("com.picsum.android.application.compose")
    id("com.picsum.kotlin.android")
    id("com.picsum.hilt")
}

android {
    namespace = "com.example.picsum"
    defaultConfig {
        testInstrumentationRunner = "com.example.picsum.CustomTestRunner"
    }
    signingConfigs {
        create("release") {
            storeFile = file(System.getProperty("user.home") + "/.android/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    runtimeOnly(project(":ui:photos-list"))
    runtimeOnly(project(":ui:photo-view"))

    implementation(project(":common:screens"))
    implementation(project(":libs:domain"))

    implementation(libs.coil.coil)
    implementation(libs.circuit.backstack)
    implementation(libs.circuit.foundation)

    androidTestImplementation(project(":common:testing"))
    androidTestImplementation(project(":libs:data"))
    androidTestImplementation(libs.moshi)
    androidTestImplementation(libs.core.ktx)

    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
