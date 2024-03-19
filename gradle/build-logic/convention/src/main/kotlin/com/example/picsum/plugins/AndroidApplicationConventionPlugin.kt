package com.example.picsum.plugins

import com.example.picsum.configureAndroid
import com.example.picsum.configureDetekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.gradle.android.cache-fix")
                apply("io.gitlab.arturbosch.detekt")
            }
            configureAndroid()
            configureDetekt(extensions.getByType<DetektExtension>())
        }
    }
}
