package com.example.picsum.plugins

import com.android.build.gradle.LibraryExtension
import com.example.picsum.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.gradle.android.cache-fix")
            }
            extensions.configure<LibraryExtension> {
                configureAndroid()

                defaultConfig.targetSdk = 34
                testOptions.animationsDisabled = true
            }
        }
    }
}
