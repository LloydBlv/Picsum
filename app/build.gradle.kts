plugins {
    id("com.picsum.android.application")
    id("com.picsum.android.application.compose")
    id("com.picsum.kotlin.android")
    id("com.picsum.hilt")
}

android {
    namespace = "com.example.picsum"
    buildTypes {
        release {
            isMinifyEnabled = false
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
    implementation(project(":common:screens"))

    implementation(libs.circuit.backstack)
    implementation(libs.circuit.foundation)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
