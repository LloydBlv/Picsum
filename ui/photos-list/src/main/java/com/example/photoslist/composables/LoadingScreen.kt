package com.example.photoslist.composables

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
internal fun BoxScope.LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier.size(32.dp)
            .align(Alignment.Center)
            .testTag("loading")
    )

}