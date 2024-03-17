plugins {
    id("com.picsum.kotlin.android")
    id("com.picsum.android.library")
    id("com.picsum.hilt")
    id("com.picsum.android.library.compose")
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.example.photoview"
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    ksp {
        arg("circuit.codegen.mode", "hilt")
    }
}

dependencies {
    implementation(project(":libs:domain"))
    implementation(project(":common:screens"))
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.junit)
    testImplementation(libs.assertk)

    implementation(libs.coil.compose)
    testImplementation(libs.coil.test)

    implementation(libs.dagger)

    implementation(libs.circuit.runtime.presenter)
    implementation(libs.circuit.runtime.ui)
    testImplementation(libs.circuit.test)
    api(libs.circuit.codegen.annotations)
    ksp(libs.circuit.codegen)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.rule)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
