package com.example.photoview.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.photoview.PhotoViewUiState

@Composable
internal fun LandscapePhotoView(
    state: PhotoViewUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        PicsumImage(
            remotePhoto = state.remotePhoto,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(state.remotePhoto.size.ratio),
        )
        AuthorNameText(state.authorName)
    }
}
