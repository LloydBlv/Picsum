package com.example.picsum.plugins

import com.example.picsum.get
import com.example.picsum.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs["hilt.android"])
                "ksp"(libs["hilt.android.compiler"])
            }
        }
    }
}