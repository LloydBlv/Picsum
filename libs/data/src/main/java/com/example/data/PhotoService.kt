package com.example.data

import retrofit2.http.GET

interface PhotoService {
    @GET("https://picsum.photos/list")
    suspend fun getPhotos(): List<PhotoDto>
}