package com.example.domain.models.repositories

import com.example.domain.models.models.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<Result<List<Photo>>>
}
