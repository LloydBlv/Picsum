package com.example.domain.models.repositories

import com.example.domain.models.Result
import com.example.domain.models.models.Photo

interface PhotoRepository {
    suspend fun getPhotos(): Result<List<Photo>>
}