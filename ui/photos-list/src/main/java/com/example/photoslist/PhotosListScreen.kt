package com.example.photoslist

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun PhotosListScreen(
    modifier: Modifier = Modifier,
    state: PhotoListUiState
) {
    AnimatedContent(
        targetState = state,
        modifier = modifier,
        label = "PhotosListScreen"
    ) {
        when (state) {
            is PhotoListUiState.Failure -> {
                FailureScreen(state.error)
            }

            PhotoListUiState.Loading -> {
                LoadingScreen()
            }

            is PhotoListUiState.Success -> {
                PhotoItems(state.photos)
            }
        }
    }


}


