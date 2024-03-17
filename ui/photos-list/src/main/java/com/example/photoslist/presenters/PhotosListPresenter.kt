package com.example.photoslist.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.domain.models.models.Photo
import com.example.domain.models.usecases.GetPhotosUseCase
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.PhotosListEvents.PhotoClicked
import com.example.photoslist.models.toUiPhoto
import com.example.screens.PhotoViewScreen
import com.example.screens.PhotosListScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import com.slack.circuit.retained.collectAsRetainedState
class PhotosListPresenter @AssistedInject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    @Assisted private val navigator: Navigator
) : Presenter<PhotoListUiState> {
    @Composable
    override fun present(): PhotoListUiState {
        val state: Result<List<Photo>>? by getPhotosUseCase.flow.collectAsRetainedState(initial = null)
        LaunchedEffect(key1 = Unit) {
            getPhotosUseCase.invoke(Unit)
        }
        val eventSink: (PhotosListEvents) -> Unit = {
            when (it) {
                is PhotoClicked -> navigator.goTo(
                    PhotoViewScreen(
                        id = it.photo.id.id,
                        authorName = it.photo.author.name,
                        width = it.photo.size.width,
                        height = it.photo.size.height
                    )
                )

                PhotosListEvents.RetryClicked -> getPhotosUseCase.invoke(Unit)
            }
        }
        return when {
            state?.isSuccess == true -> PhotoListUiState.Success(
                photos = state!!.getOrNull()?.map(Photo::toUiPhoto).orEmpty().toPersistentList(),
                eventSink = eventSink
            )

            state?.isFailure == true -> PhotoListUiState.Failure(
                error = state?.exceptionOrNull(),
                eventSink = eventSink
            )

            else -> PhotoListUiState.Loading
        }
    }
}

@CircuitInject(PhotosListScreen::class, SingletonComponent::class)
@AssistedFactory
interface Factory {
    fun create(navigator: Navigator): PhotosListPresenter
}