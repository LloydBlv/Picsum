@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photoslist.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.photoslist.R
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.screens.PhotosListScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@CircuitInject(PhotosListScreen::class, SingletonComponent::class)
@Composable
fun PhotosListScreen(state: PhotoListUiState, modifier: Modifier = Modifier) {
    val eventSink = state.eventSink
    Scaffold(
        modifier = modifier,
        topBar = { PhotosListTopBar() },
    ) {
        PhotosListScreenContent(
            modifier = Modifier.padding(it),
            state = state,
            eventSink = eventSink,
        )
    }
}

@Composable
private fun PhotosListTopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    })
}

@Composable
internal fun PhotosListScreenContent(
    state: PhotoListUiState,
    modifier: Modifier = Modifier,
    eventSink: (PhotosListEvents) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
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
