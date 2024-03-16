package com.example.photoslist.models

import androidx.compose.runtime.Immutable
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.collections.immutable.ImmutableList


sealed interface PhotoListUiState : CircuitUiState {
    data object Loading : PhotoListUiState
    @Immutable
    data class Success(val photos: ImmutableList<UiPhoto>) : PhotoListUiState

    @Immutable
    data class Failure(val error: Throwable?) : PhotoListUiState
}
