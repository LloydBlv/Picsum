package com.example.photoslist.composables

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.screens.PhotosListScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@CircuitInject(PhotosListScreen::class, SingletonComponent::class)
@Composable
fun PhotosListScreen(
    modifier: Modifier = Modifier,
    state: PhotoListUiState
) {
    val eventSink = state.eventSink
    AnimatedContent(
        targetState = state,
        modifier = modifier,
        label = "PhotosListScreen"
    ) {
        PhotosListScreenContent(state, eventSink)
    }


}

@Composable
internal fun PhotosListScreenContent(
    state: PhotoListUiState,
    eventSink: (PhotosListEvents) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is PhotoListUiState.Failure -> {
                FailureScreen(
                    error = state.error,
                    modifier = Modifier.fillMaxSize(),
                    eventSink = eventSink,
                )
            }

            PhotoListUiState.Loading -> {
                LoadingScreen(modifier = Modifier)
            }

            is PhotoListUiState.Success -> {
                PhotoItems(state.photos, eventSink)
            }
        }
    }
}


