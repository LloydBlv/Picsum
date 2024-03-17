package com.example.photoslist.models

import androidx.compose.runtime.Immutable
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.collections.immutable.ImmutableList

sealed interface PhotoListUiState : CircuitUiState {
    val eventSink: (PhotosListEvents) -> Unit
    data object Loading : PhotoListUiState {
        override val eventSink: (PhotosListEvents) -> Unit
            get() = {}
    }

    @Immutable
    data class Success(val photos: ImmutableList<UiPhoto>, override val eventSink: (PhotosListEvents) -> Unit = {}) : PhotoListUiState

    @Immutable
    data class Failure(val error: Throwable?, override val eventSink: (PhotosListEvents) -> Unit = {}) : PhotoListUiState
}
