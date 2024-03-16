package com.example.screens

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object PhotosListScreen : Screen

@Parcelize
data class PhotoViewScreen(
    val id: Int, val authorName: String,
    val width: Int,
    val height: Int
) : Screen