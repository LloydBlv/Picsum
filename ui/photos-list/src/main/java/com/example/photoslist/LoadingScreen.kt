package com.example.photoslist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
internal fun AnimatedContentScope.LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier.size(32.dp)
            .testTag("loading")
    )

}