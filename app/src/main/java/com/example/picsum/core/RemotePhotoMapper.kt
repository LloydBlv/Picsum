package com.example.picsum.core

import coil.map.Mapper
import coil.request.Options
import com.example.domain.models.models.RemotePhoto

object RemotePhotoMapper : Mapper<RemotePhoto, String> {
    override fun map(data: RemotePhoto, options: Options): String? {
        return "https://picsum.photos/${data.size.width}/${data.size.height}?image=${data.id.id}"
    }
}
