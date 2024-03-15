package com.example.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

object TestData {
    fun Moshi.provideTestPhotoDtos(): List<PhotoDto> {
        val response = loadResponse("photo-list-response.json")
        val type: Type = Types.newParameterizedType(List::class.java, PhotoDto::class.java)
        val photosList = adapter<List<PhotoDto>>(type).fromJson(response)!!
        return photosList
    }
}