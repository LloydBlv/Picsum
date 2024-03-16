package com.example.domain.models.models

data class Size(
    val width: Int,
    val height: Int,
) {
    val isLandscape: Boolean
        get() = width > height

    val ratio: Float
        get() = width.toFloat() / height
}