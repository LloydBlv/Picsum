package com.example.testing

import com.example.data.PhotoDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.BufferedSource
import java.lang.reflect.Type

object TestData {
    fun Moshi.provideTestPhotoDtos(): List<PhotoDto> {
        val type: Type = Types.newParameterizedType(List::class.java, PhotoDto::class.java)
        val photosList = adapter<List<PhotoDto>>(type).fromJson(getTestResponse())!!
        return photosList
    }

    private fun getTestResponse(): BufferedSource {
        return loadResponse("photo-list-response.json")
    }
    fun getTestResponseString(): String {
        return loadResponseString("photo-list-response.json")
    }
}
