package com.example.photoview.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.photoview.PhotoViewUiState

@Composable
internal fun PortraitPhotoView(state: PhotoViewUiState, modifier: Modifier, imageUrl: String) {
    Column(modifier = modifier) {
        PicsumImage(
            url = imageUrl,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(),
        )
        AuthorNameText(state.authorName, modifier = Modifier.weight(0.2f))
    }
}
