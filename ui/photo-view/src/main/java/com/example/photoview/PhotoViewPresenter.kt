package com.example.photoview

import androidx.compose.runtime.Composable
import com.example.domain.models.models.Size
import com.example.screens.PhotoViewScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

internal class PhotoViewPresenter(
    private val screen: PhotoViewScreen,
    private val navigator: Navigator
) : Presenter<PhotoViewUiState> {
    @Composable
    override fun present(): PhotoViewUiState {
        fun eventSink(event: PhotoViewEvents) {
            when (event) {
                PhotoViewEvents.OnBackPressed -> navigator.pop()
            }
        }
        return PhotoViewUiState(
            size = Size(screen.width, screen.height),
            imageUrl = createImageUrl(screen.width, screen.height, screen.id),
            authorName = screen.authorName,
            eventSink = ::eventSink
        )
    }

    private fun createImageUrl(width: Int, height: Int, id: Int): String {
        return "https://picsum.photos/$width/$height?image=$id"
    }
}