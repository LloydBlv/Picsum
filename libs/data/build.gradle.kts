plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":libs:domain"))
    testImplementation(project(":common:testing"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation("com.slack.eithernet:eithernet:1.8.1")
    testImplementation("app.cash.turbine:turbine:1.1.0")
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