plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint()
    }

    kotlinGradle {
        target("*.kts")
        ktlint()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("root") {
            id = "com.picsum.root"
            implementationClass = "com.example.picsum.plugins.RootConventionPlugin"
        }
        register("kotlinAndroid") {
            id = "com.picsum.kotlin.android"
            implementationClass = "com.example.picsum.plugins.KotlinAndroidConventionPlugin"
        }
        register("androidApplication") {
            id = "com.picsum.android.application"
            implementationClass = "com.example.picsum.plugins.AndroidApplicationConventionPlugin1"
        }
        register("androidLibrary") {
            id = "com.picsum.android.library"
            implementationClass = "com.example.picsum.plugins.AndroidLibraryConventionPlugin"
        }
        register("hiltAndroid") {
            id = "com.picsum.hilt"
            implementationClass = "com.example.picsum.plugins.HiltConventionPlugin"
        }
    }
}