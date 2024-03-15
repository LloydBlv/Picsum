package com.example.data

import com.example.domain.models.models.Photo
import com.example.domain.models.repositories.PhotoRepository
import com.example.domain.models.Result
import com.example.domain.models.toSuccess
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class PhotoRepositoryFake(
    private val moshi: Moshi = Moshi.Builder().build()
): PhotoRepository {
    override suspend fun getPhotos(): Result<List<Photo>> {
        val response = loadResponse("photo-list-response.json")
        val type: Type = Types.newParameterizedType(List::class.java, PhotoDto::class.java)
        val adapter: JsonAdapter<List<PhotoDto>> = moshi.adapter(type)
        val photosList = adapter.fromJson(response)!!
        return photosList.map(PhotoDto::toPhoto).toSuccess()
    }
}