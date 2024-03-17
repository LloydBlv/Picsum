package com.example.photoview.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.photoview.R

@Composable
internal fun PicsumImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = stringResource(id = R.string.picsumimage),
    )
}
