package com.example.photoview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun PhotoViewScreen(
    modifier: Modifier = Modifier,
    state: PhotoViewUiState
) {
    Scaffold {
        PhotoViewContent(state = state, modifier = Modifier.padding(it))
    }

}

@Composable
private fun PhotoViewContent(state: PhotoViewUiState, modifier: Modifier) {
    if (state.size.isLandscape) {
        LandscapePhotoView(state = state, modifier = modifier)
    } else {
        PortraitPhotoView(state = state, modifier = modifier)
    }
}

@Composable
fun PortraitPhotoView(state: PhotoViewUiState, modifier: Modifier) {
    Column {
        PicsumImage()
        AuthorNameText(state.authorName)
    }
}

@Composable
fun AuthorNameText(authorName: String) {
    Text(text = authorName, modifier = Modifier.fillMaxWidth())
}

@Composable
fun PicsumImage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

    }
}

@Composable
fun LandscapePhotoView(state: PhotoViewUiState, modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        PicsumImage()
        AuthorNameText(state.authorName)
    }
}
