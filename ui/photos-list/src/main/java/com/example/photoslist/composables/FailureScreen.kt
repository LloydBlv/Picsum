package com.example.photoslist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.photoslist.R
import com.example.photoslist.models.PhotosListEvents

@Composable
internal fun FailureScreen(error: Throwable?, eventSink: (PhotosListEvents) -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .testTag("error")
    ) {
        Button(onClick = { eventSink.invoke(PhotosListEvents.RetryClicked) }) {
            Text(text = stringResource(R.string.retry))
        }
        Text(text = "${error?.message}")
    }
}