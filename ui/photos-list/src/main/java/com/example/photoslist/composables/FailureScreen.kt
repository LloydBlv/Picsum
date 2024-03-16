package com.example.photoslist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
internal fun FailureScreen(error: Throwable?) {
    Box(modifier = Modifier.padding(16.dp).testTag("error")) {
        Text(text = "${error?.message}")
    }
}