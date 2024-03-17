@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photoview.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.photoview.PhotoViewUiState


@Composable
internal fun PhotoViewScreen(
    modifier: Modifier = Modifier,
    state: PhotoViewUiState
) {
    Scaffold(topBar = {
        DetailTopBar(onBackPressed = {})
    }) {
        PhotoViewContent(state = state, modifier = Modifier.padding(it))
    }

}

@Composable
private fun PhotoViewContent(state: PhotoViewUiState, modifier: Modifier) {
    if (state.size.isLandscape) {
        LandscapePhotoView(state = state, modifier = modifier, imageUrl = state.imageUrl)
    } else {
        PortraitPhotoView(state = state, modifier = modifier, imageUrl = state.imageUrl)
    }
}

