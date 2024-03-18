package com.example.testing

import android.content.Context
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

private const val ASSET_BASE_PATH = "../app/src/debug/assets/"

fun Context.loadAssets(fileName: String): BufferedSource {
    return try {
        File("$ASSET_BASE_PATH$fileName").source().buffer()
    } catch (e: Exception) {
        assets.open(fileName).source().buffer()
    }
}
