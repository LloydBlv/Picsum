package com.example.data

import com.example.domain.models.Photo
import com.example.domain.models.PhotoRepository

class GetPhotosUseCase constructor(private val repository: PhotoRepository) {
    suspend operator fun invoke(): List<Photo> {
        return repository.getPhotos()
    }
}