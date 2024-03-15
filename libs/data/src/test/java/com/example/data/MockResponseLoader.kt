package com.example.data

import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File


private const val BASE_DIRECTORY = "src/test/resources/"
fun loadResponse(fileName: String): BufferedSource {
    val path = "$BASE_DIRECTORY$fileName"
    val file = File(path)
    return file.source().buffer()
}