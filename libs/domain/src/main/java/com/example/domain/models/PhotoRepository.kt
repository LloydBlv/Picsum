package com.example.domain.models

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
}