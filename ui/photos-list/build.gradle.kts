plugins {
    id("com.picsum.kotlin.android")
    id("com.picsum.android.library")
    id("com.picsum.hilt")
    id("com.picsum.android.library.compose")
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.example.photoslist"
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
    implementation(project(":common:screens"))
    implementation(project(":libs:domain"))
    runtimeOnly(project(":libs:data"))

    implementation(libs.dagger)

    testImplementation(project(":common:testing"))
    testImplementation(libs.assertk)
    testImplementation(libs.moshi)

    implementation(libs.circuit.runtime.presenter)
    implementation(libs.circuit.runtime.ui)
    implementation(libs.circuit.retained)
    testImplementation(libs.circuit.test)
    api(libs.circuit.codegen.annotations)
    ksp(libs.circuit.codegen)

    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.rule)

    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.robolectric)
    debugImplementation(libs.androidx.ui.test.manifest)
}
