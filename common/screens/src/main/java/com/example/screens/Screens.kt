package com.example.screens

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object PhotosListScreen : Screen

@Parcelize
data class ViewPhotoScreen(val id: Int) : Screen