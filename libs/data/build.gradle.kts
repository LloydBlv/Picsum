plugins {
    id("com.picsum.kotlin.android")
    id("com.picsum.android.library")
    id("com.picsum.hilt")
}

android {
    namespace = "com.example.data"
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
    implementation(project(":libs:domain"))
    testImplementation(project(":common:testing"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.eithernet)
    testImplementation(libs.turbine)
    implementation(libs.retrofit)
    api(libs.moshi)
    testImplementation(libs.mockwebserver)
    implementation(libs.converter.moshi)
    testImplementation(libs.assertk)
    testImplementation(libs.kotlinx.coroutines.test)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
