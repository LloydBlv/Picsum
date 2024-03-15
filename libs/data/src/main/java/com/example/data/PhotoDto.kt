package com.example.data

import com.example.domain.models.Author
import com.example.domain.models.FileName
import com.example.domain.models.Id
import com.example.domain.models.Photo
import com.example.domain.models.Size
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

fun PhotoDto.toPhoto(): Photo {
    return Photo(
        size = Size(width ?: 0, height ?: 0),
        fileName = FileName(fileName.orEmpty()),
        id = Id(id ?: 0),
        author = Author(author.orEmpty(), authorUrl.orEmpty())
    )
}