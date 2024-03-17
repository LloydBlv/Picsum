plugins {
    id("com.picsum.kotlin.android")
    id("com.picsum.android.library")
    id("com.picsum.hilt")
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
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.5.10"
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
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.rule)

    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation("junit:junit:4.13.2")
    testImplementation(libs.robolectric)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}