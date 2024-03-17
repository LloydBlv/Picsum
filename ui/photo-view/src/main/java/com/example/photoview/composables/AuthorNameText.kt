package com.example.photoview.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun AuthorNameText(authorName: String, modifier: Modifier = Modifier) {
    Text(
        text = authorName,
        modifier = modifier.fillMaxWidth().padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
    )
}
