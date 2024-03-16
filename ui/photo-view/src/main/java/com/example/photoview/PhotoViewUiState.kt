package com.example.photoview

import com.example.domain.models.models.Size

data class PhotoViewUiState(
    val size: Size,
    val imageUrl: String,
    val authorName: String
)
