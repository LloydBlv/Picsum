package com.example.data

import com.example.domain.models.PhotoRepository

class PhotoRepositoryDefault constructor(
    private val photoService: PhotoService
): PhotoRepository {
    override suspend fun getPhotos() = photoService.getPhotos().map(PhotoDto::toPhoto)
}