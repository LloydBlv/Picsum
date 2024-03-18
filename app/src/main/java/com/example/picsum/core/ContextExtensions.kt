package com.example.picsum.core

import android.app.ActivityManager
import android.content.Context
import androidx.core.content.getSystemService

fun Context.isHighPerformingDevice(): Boolean {
    val activityManager = getSystemService<ActivityManager>() ?: return false

    val memInfo = ActivityManager.MemoryInfo()
    activityManager.getMemoryInfo(memInfo)
    val lessThan3GbOfRam = memInfo.totalMem < 3L * 1024 * 1024 * 1024
    return !activityManager.isLowRamDevice &&
        !lessThan3GbOfRam &&
        activityManager.memoryClass >= 128 &&
        Runtime.getRuntime().availableProcessors() >= 4
}
