package com.example.domain.models.usecases

import com.example.domain.models.repositories.PhotoRepository
import com.example.domain.models.Result
import com.example.domain.models.models.Photo

class GetPhotosUseCase(private val repository: PhotoRepository) {
    suspend operator fun invoke(): Result<List<Photo>> {
        return repository.getPhotos()
    }
}