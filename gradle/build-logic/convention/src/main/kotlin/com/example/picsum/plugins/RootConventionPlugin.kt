package com.example.picsum.plugins

import com.example.picsum.configureSpotless
import org.gradle.api.Plugin
import org.gradle.api.Project

class RootConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        configureSpotless()
    }
}