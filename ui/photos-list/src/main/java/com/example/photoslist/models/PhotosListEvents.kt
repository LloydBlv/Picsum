package com.example.photoslist.models

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface PhotosListEvents : CircuitUiEvent {
    data class PhotoClicked(val photo: UiPhoto) : PhotosListEvents
}