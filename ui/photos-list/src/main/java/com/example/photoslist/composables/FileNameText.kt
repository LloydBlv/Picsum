package com.example.photoslist.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.photoslist.models.UiPhoto

@Composable
internal fun FileNameText(
    photo: UiPhoto,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        color = color,
        text = photo.fileName.value,
        modifier = modifier.padding(8.dp),
    )
}
