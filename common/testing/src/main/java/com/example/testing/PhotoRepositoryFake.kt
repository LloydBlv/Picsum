package com.example.testing

import android.annotation.SuppressLint
import com.example.data.PhotoDto
import com.example.data.toPhoto
import com.example.domain.models.models.Photo
import com.example.domain.models.repositories.PhotoRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PhotoRepositoryFake(
    private val moshi: Moshi = Moshi.Builder().build(),
) : PhotoRepository {

    var exception: Throwable? = null

    @SuppressLint("VisibleForTests")
    override fun getPhotos(): Flow<Result<List<Photo>>> {
        if (exception != null) {
            return flowOf(Result.failure(exception!!))
        }
        val response = loadResponse("photo-list-response.json")
        val type: Type = Types.newParameterizedType(List::class.java, PhotoDto::class.java)
        val adapter: JsonAdapter<List<PhotoDto>> = moshi.adapter(type)
        val photosList = adapter.fromJson(response)!!
        return flowOf(Result.success(photosList.map(PhotoDto::toPhoto)))
    }
}
