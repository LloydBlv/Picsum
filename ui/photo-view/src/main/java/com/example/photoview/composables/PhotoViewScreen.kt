@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photoview.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.photoview.PhotoViewEvents
import com.example.photoview.PhotoViewUiState
import com.example.screens.PhotoViewScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent

@Composable
@CircuitInject(PhotoViewScreen::class, SingletonComponent::class)
internal fun PhotoViewScreen(state: PhotoViewUiState, modifier: Modifier = Modifier) {
    val eventSink = state.eventSink
    Scaffold(
        modifier = modifier,
        topBar = { DetailTopBar(onBackPressed = { eventSink.invoke(PhotoViewEvents.OnBackPressed) }) },
    ) {
        PhotoViewContent(state = state, modifier = Modifier.padding(it))
    }
}

@Composable
private fun PhotoViewContent(state: PhotoViewUiState, modifier: Modifier = Modifier) {
    if (state.remotePhoto.size.isLandscape) {
        LandscapePhotoView(state = state, modifier = modifier)
    } else {
        PortraitPhotoView(state = state, modifier = modifier)
    }
}
