package com.example.domain.models

class GetPhotosUseCase(private val repository: PhotoRepository) {
    suspend operator fun invoke(): Result<List<Photo>> {
        return repository.getPhotos()
    }
}