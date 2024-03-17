package com.example.photoview

import androidx.compose.runtime.Composable
import com.example.domain.models.models.Size
import com.example.screens.PhotoViewScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent

class PhotoViewPresenter @AssistedInject constructor(
    @Assisted private val screen: PhotoViewScreen,
    @Assisted private val navigator: Navigator,
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
            eventSink = ::eventSink,
        )
    }

    private fun createImageUrl(width: Int, height: Int, id: Int): String {
        return "https://picsum.photos/$width/$height?image=$id"
    }
}

@CircuitInject(PhotoViewScreen::class, SingletonComponent::class)
@AssistedFactory
interface Factory {
    fun create(screen: PhotoViewScreen, navigator: Navigator): PhotoViewPresenter
}
