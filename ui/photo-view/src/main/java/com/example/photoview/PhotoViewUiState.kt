package com.example.photoview

import androidx.compose.runtime.Immutable
import com.example.domain.models.models.RemotePhoto
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

@Immutable
data class UiRemotePhoto(
    val remotePhoto: RemotePhoto,
)

@Immutable
data class PhotoViewUiState(
    val uiRemotePhoto: UiRemotePhoto,
    val authorName: String,
    val eventSink: (PhotoViewEvents) -> Unit,
) : CircuitUiState

sealed interface PhotoViewEvents : CircuitUiEvent {
    data object OnBackPressed : PhotoViewEvents
}
