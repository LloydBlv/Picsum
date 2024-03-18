package com.example.photoview.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.photoview.R
import com.example.photoview.UiRemotePhoto

@Composable
internal fun PicsumImage(
    remotePhoto: UiRemotePhoto,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        modifier = modifier,
        model = remotePhoto.remotePhoto,
        contentDescription = stringResource(id = R.string.picsumimage),
    )
}
