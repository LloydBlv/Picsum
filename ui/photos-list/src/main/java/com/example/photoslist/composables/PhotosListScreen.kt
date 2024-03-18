@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photoslist.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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
        topBar = {
            PhotosListTopBar(
                showStaggeredView = (state as? PhotoListUiState.Success)?.showStaggeredView,
                eventSink = eventSink,
            )
        },
    ) {
        PhotosListScreenContent(
            modifier = Modifier.padding(it),
            state = state,
            eventSink = eventSink,
        )
    }
}

@Composable
private fun PhotosListTopBar(showStaggeredView: Boolean?, eventSink: (PhotosListEvents) -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            if (showStaggeredView != null) {
                IconsToggleButton(
                    showStaggeredView = showStaggeredView == true,
                    onClick = { eventSink.invoke(PhotosListEvents.ToggleListViewType) },
                )
            }
        },
    )
}

@Composable
fun IconsToggleButton(
    showStaggeredView: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = remember(showStaggeredView) {
        if (!showStaggeredView) {
            Icons.Default.GridView
        } else {
            Icons.Default.TextFields
        }
    }
    IconButton(
        modifier = modifier.testTag("toggle_view_button"),
        onClick = onClick,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
    }
}

@Composable
internal fun PhotosListScreenContent(
    state: PhotoListUiState,
    modifier: Modifier = Modifier,
    eventSink: (PhotosListEvents) -> Unit,
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
                if (state.showStaggeredView) {
                    GridView(state.photos, eventSink)
                } else {
                    PhotoItems(state.photos, eventSink)
                }
            }
        }
    }
}
