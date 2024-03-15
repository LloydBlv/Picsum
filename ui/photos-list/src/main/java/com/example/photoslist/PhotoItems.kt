package com.example.photoslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
internal fun PhotoItems(photos: List<UiPhoto>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("photo_list")
    ) {
        itemsIndexed(photos) { index, photo ->
            PhotoItem(index, photo)
        }
    }
}

@Composable
private fun PhotoItem(index: Int, photo: UiPhoto) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("photo_item_$index")
    ) {
        Text(text = photo.author.name)
        Text(text = photo.fileName.value)
    }
}