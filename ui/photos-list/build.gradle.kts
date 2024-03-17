plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.roborazzi)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.photoslist"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    testOptions.unitTests.isIncludeAndroidResources = true

    ksp {
        arg("circuit.codegen.mode", "hilt")
    }
}

dependencies {
    implementation(project(":common:screens"))
    implementation(project(":libs:domain"))
    runtimeOnly(project(":libs:data"))

    implementation(libs.dagger)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

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