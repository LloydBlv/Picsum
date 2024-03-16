package com.example.photoslist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.UiPhoto

@Composable
internal fun PhotoItems(photos: List<UiPhoto>, eventSink: (PhotosListEvents) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("photo_list")
    ) {
        itemsIndexed(photos) { index, photo ->
            PhotoItem(index, photo, eventSink)
        }
    }
}

@Composable
private fun PhotoItem(index: Int, photo: UiPhoto, eventSink: (PhotosListEvents) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { eventSink(PhotosListEvents.PhotoClicked(photo)) }
            .testTag("photo_item_$index")
    ) {
        Text(text = photo.author.name)
        Text(text = photo.fileName.value)
    }
}