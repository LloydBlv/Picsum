package com.example.photoslist.models

import androidx.compose.runtime.Immutable
import com.example.domain.models.models.Author
import com.example.domain.models.models.FileName
import com.example.domain.models.models.Id
import com.example.domain.models.models.Photo
import com.example.domain.models.models.Size

@Immutable
data class UiPhoto(
    val size: Size,
    val fileName: FileName,
    val id: Id,
    val author: Author,
)

fun Photo.toUiPhoto(): UiPhoto {
    return UiPhoto(
        size = size,
        fileName = fileName,
        id = id,
        author = author,
    )
}
