package com.example.photoview.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.photoview.PhotoViewUiState

@Composable
internal fun PortraitPhotoView(
    state: PhotoViewUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PicsumImage(
            remotePhoto = state.remotePhoto,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
                .fillMaxSize(),
        )
        AuthorNameText(state.authorName, modifier = Modifier.weight(0.2f))
    }
}
