package com.example.testing

import android.annotation.SuppressLint
import android.content.Context
import com.example.data.PhotoDto
import com.example.data.toPhoto
import com.example.domain.models.models.Photo
import com.example.domain.models.repositories.PhotoRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.FileNotFoundException
import java.lang.reflect.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okio.BufferedSource

class PhotoRepositoryFake(
    private val moshi: Moshi = Moshi.Builder().build(),
    private val context: Context? = null,
) : PhotoRepository {

    var exception: Throwable? = null

    @SuppressLint("VisibleForTests")
    override fun getPhotos(): Flow<Result<List<Photo>>> {
        if (exception != null) {
            return flowOf(Result.failure(exception!!))
        }
        val fileName = "photo-list-response.json"
        val response: BufferedSource = try {
            loadResponse(fileName)
        } catch (e: FileNotFoundException) {
            println(e.message)
            context?.loadAssets(fileName)!!
        }
        val type: Type = Types.newParameterizedType(List::class.java, PhotoDto::class.java)
        val adapter: JsonAdapter<List<PhotoDto>> = moshi.adapter(type)
        val photosList = adapter.fromJson(response)!!
        return flowOf(Result.success(photosList.map(PhotoDto::toPhoto)))
    }
}
