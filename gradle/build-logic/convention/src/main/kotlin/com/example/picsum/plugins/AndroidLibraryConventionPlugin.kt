package com.example.picsum.plugins

import com.android.build.gradle.LibraryExtension
import com.example.picsum.configureAndroid
import com.example.picsum.configureDetekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.gradle.android.cache-fix")
                apply("io.gitlab.arturbosch.detekt")
            }
            extensions.configure<LibraryExtension> {
                configureAndroid()
                defaultConfig.targetSdk = 34
                testOptions.animationsDisabled = true
            }
            configureDetekt(extensions.getByType<DetektExtension>())
        }
    }
}
