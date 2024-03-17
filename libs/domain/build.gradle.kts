plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
dependencies {
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.jvm)
}