package com.example.photoslist

import androidx.compose.runtime.Immutable
import com.example.domain.models.models.Author
import com.example.domain.models.models.FileName
import com.example.domain.models.models.Id
import com.example.domain.models.models.Size
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class UiPhoto(
    val size: Size,
    val fileName: FileName,
    val id: Id,
    val author: Author
)
sealed interface PhotoListUiState {
    data object Loading : PhotoListUiState
    @Immutable
    data class Success(val photos: ImmutableList<UiPhoto>) : PhotoListUiState

    @Immutable
    data class Failure(val error: Throwable?) : PhotoListUiState
}
