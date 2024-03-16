@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.photoview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun PhotoViewScreen(
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
fun DetailTopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(text = "Photo View")
        })
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
    Column(modifier = modifier) {
        PicsumImage(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize()
        )
        AuthorNameText(state.authorName, modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun AuthorNameText(authorName: String, modifier: Modifier = Modifier) {
    Text(text = authorName, modifier = modifier.fillMaxWidth())
}

@Composable
fun PicsumImage(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.Red)) {

    }
}

@Composable
fun LandscapePhotoView(state: PhotoViewUiState, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        PicsumImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(state.size.ratio)
        )
        AuthorNameText(state.authorName)
    }
}
