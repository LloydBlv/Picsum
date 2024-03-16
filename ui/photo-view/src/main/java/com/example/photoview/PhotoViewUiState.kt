package com.example.photoview

import com.example.domain.models.models.Size
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

internal data class PhotoViewUiState(
    val size: Size,
    val imageUrl: String,
    val authorName: String,
    val eventSink: (PhotoViewEvents) -> Unit,

): CircuitUiState


internal sealed interface PhotoViewEvents: CircuitUiEvent {
    data object OnBackPressed: PhotoViewEvents
}
