package com.example.picsum.core

import coil.map.Mapper
import coil.request.Options
import com.example.domain.models.models.RemoteThumbnail

object RemoteThumbnailMapper : Mapper<RemoteThumbnail, String> {
    override fun map(data: RemoteThumbnail, options: Options): String? {
        val width = options.size.width
        val height = options.size.height
        return "https://picsum.photos/$width/$height?image=${data.id.id}"
    }
}
