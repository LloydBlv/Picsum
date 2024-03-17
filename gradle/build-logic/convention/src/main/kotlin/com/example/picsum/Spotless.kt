package com.example.picsum

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.LineEnding
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
    with(pluginManager) {
        apply("com.diffplug.spotless")
    }

    spotless {
        lineEndings = LineEnding.PLATFORM_NATIVE
        val ktlintVersion = libs.findVersion("ktlint").get().requiredVersion
        kotlin {
            target("src/**/*.kt")
            ktlint(ktlintVersion)
        }
        kotlinGradle {
            target("*.kts")
            ktlint(ktlintVersion)
        }
    }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)
