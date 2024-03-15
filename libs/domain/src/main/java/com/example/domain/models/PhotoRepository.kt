package com.example.domain.models

interface PhotoRepository {
    suspend fun getPhotos(): Result<List<Photo>>
}