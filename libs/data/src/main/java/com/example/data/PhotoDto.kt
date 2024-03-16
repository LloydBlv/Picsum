package com.example.data

import androidx.annotation.VisibleForTesting
import com.example.domain.models.models.Author
import com.example.domain.models.models.FileName
import com.example.domain.models.models.Id
import com.example.domain.models.models.Photo
import com.example.domain.models.models.Size
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PhotoDto(
    @Json(name = "width") val width: Int?,
    @Json(name = "height") val height: Int?,
    @Json(name = "filename") val fileName: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "author") val author: String?,
    @Json(name = "author_url") val authorUrl: String?,
)


@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
fun PhotoDto.toPhoto(): Photo {
    return Photo(
        size = Size(width ?: 0, height ?: 0),
        fileName = FileName(fileName.orEmpty()),
        id = Id(id ?: 0),
        author = Author(author.orEmpty(), authorUrl.orEmpty())
    )
}