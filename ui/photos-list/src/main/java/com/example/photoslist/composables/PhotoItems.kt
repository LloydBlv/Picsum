package com.example.photoslist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.UiPhoto
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun BoxScope.PhotoItems(photos: ImmutableList<UiPhoto>, eventSink: (PhotosListEvents) -> Unit) {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .testTag("photo_list"),
    ) {
        itemsIndexed(photos) { index, photo ->
            PhotoItem(index = index, photo = photo, eventSink = eventSink)
            if (index != photos.lastIndex) {
                VerticalDivider(
                    color = Color.DarkGray.copy(alpha = 0.5f),
                )
            }
        }
    }
}

@Composable
internal fun PhotoItem(
    index: Int,
    photo: UiPhoto,
    modifier: Modifier = Modifier,
    eventSink: (PhotosListEvents) -> Unit,
) {
    ListItem(
        modifier = modifier
            .clickable { eventSink(PhotosListEvents.PhotoClicked(photo)) }
            .testTag("photo_item_$index"),
        headlineContent = { FileNameText(photo) },
    )
}

@Composable
private fun FileNameText(photo: UiPhoto) {
    Text(text = photo.fileName.value, modifier = Modifier.padding(8.dp))
}
