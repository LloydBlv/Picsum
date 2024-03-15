package com.example.data

import com.example.domain.models.Photo
import com.example.domain.models.PhotoRepository
import com.example.domain.models.Result

class GetPhotosUseCase(private val repository: PhotoRepository) {
    suspend operator fun invoke(): Result<List<Photo>> {
        return repository.getPhotos()
    }
}