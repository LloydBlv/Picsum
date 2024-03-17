package com.example.photoslist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.photoslist.R
import com.example.photoslist.models.PhotosListEvents

@Composable
internal fun BoxScope.FailureScreen(
    error: String,
    modifier: Modifier = Modifier,
    eventSink: (PhotosListEvents) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .testTag("error"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { eventSink.invoke(PhotosListEvents.RetryClicked) }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
