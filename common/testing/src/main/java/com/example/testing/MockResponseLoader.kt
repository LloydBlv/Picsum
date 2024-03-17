package com.example.testing

import java.io.File
import okio.BufferedSource
import okio.buffer
import okio.source

private const val BASE_DIRECTORY = "../../common/testing/src/test/resources/"
fun loadResponse(fileName: String): BufferedSource {
    val path = "$BASE_DIRECTORY$fileName"
    val file = File(path)
    return file.source().buffer()
}
fun loadResponseString(fileName: String): String {
    val path = "$BASE_DIRECTORY$fileName"
    return File(path).bufferedReader().use { it.readText() }
}
