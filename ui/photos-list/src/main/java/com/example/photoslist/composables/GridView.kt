package com.example.photoslist.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.UiPhoto
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
internal fun BoxScope.GridView(
    photos: ImmutableList<UiPhoto>,
    eventSink: (PhotosListEvents) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val scope = rememberCoroutineScope()
        val staggeredState = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("photo_grid"),
            state = staggeredState,
        ) {
            items(
                items = photos,
                key = { photo: UiPhoto -> photo.id.id },
                itemContent = { photo -> GridPhotoItem(photo = photo, eventSink = eventSink) },
            )
        }

        val showButton by remember {
            derivedStateOf {
                staggeredState.firstVisibleItemIndex > 0
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
                    staggeredState.animateScrollToItem(0)
                }
            })
        }
    }
}

@Composable
internal fun GridPhotoItem(
    photo: UiPhoto,
    modifier: Modifier = Modifier,
    eventSink: (PhotosListEvents) -> Unit,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = photo.remoteThumbnail,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(photo.size.ratio)
                .wrapContentHeight()
                .clickable { eventSink(PhotosListEvents.PhotoClicked(photo)) },
            contentDescription = null,
        )
        FileNameText(
            photo = photo,
            color = Color.White,
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.25f))
                .padding(4.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}
