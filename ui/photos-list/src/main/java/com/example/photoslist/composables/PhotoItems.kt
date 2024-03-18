package com.example.photoslist.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.UiPhoto
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
internal fun BoxScope.PhotoItems(
    photos: ImmutableList<UiPhoto>,
    eventSink: (PhotosListEvents) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val scope = rememberCoroutineScope()
        val state = rememberLazyListState()

        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .testTag("photo_list"),
        ) {
            items(
                items = photos,
                key = { photo -> photo.id.id },
                itemContent = { photo -> PhotoItem(photo = photo, eventSink = eventSink) },
            )
        }
        val showButton by remember {
            derivedStateOf {
                state.firstVisibleItemIndex > 0
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            })
        }
    }
}

@Composable
internal fun PhotoItem(
    photo: UiPhoto,
    modifier: Modifier = Modifier,
    eventSink: (PhotosListEvents) -> Unit,
) {
    ListItem(
        modifier = modifier
            .clickable { eventSink(PhotosListEvents.PhotoClicked(photo)) }
            .testTag("photo_item_${photo.id.id}"),
        headlineContent = { FileNameText(photo) },
    )
}
