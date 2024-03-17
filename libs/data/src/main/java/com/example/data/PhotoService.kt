package com.example.data

import com.slack.eithernet.ApiResult
import retrofit2.http.GET

interface PhotoService {
    @GET("https://picsum.photos/list")
    suspend fun getPhotos(): ApiResult<List<PhotoDto>, Unit>
}
