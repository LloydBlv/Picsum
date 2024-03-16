package com.example.data

import com.example.domain.models.repositories.PhotoRepository

class PhotoRepositoryDefault constructor(
    private val photoService: PhotoService
): PhotoRepository {
    override suspend fun getPhotos() = photoService.getPhotos().mapValue { it.map(PhotoDto::toPhoto) }
}