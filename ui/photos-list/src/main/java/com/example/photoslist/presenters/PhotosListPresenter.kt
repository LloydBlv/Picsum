package com.example.photoslist.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.domain.models.models.Photo
import com.example.domain.models.usecases.GetPhotosUseCase
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.PhotosListEvents.PhotoClicked
import com.example.photoslist.models.toUiPhoto
import com.example.screens.PhotoViewScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList


class PhotosListPresenter(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val navigator: Navigator
) : Presenter<PhotoListUiState> {
    @Composable
    override fun present(): PhotoListUiState {
        val state: Result<List<Photo>>? by getPhotosUseCase.flow.collectAsState(initial = null)
        LaunchedEffect(key1 = Unit) {
            getPhotosUseCase.invoke(Unit)
        }
        val eventSink: (PhotosListEvents) -> Unit = {
            when (it) {
                is PhotoClicked -> navigator.goTo(PhotoViewScreen(it.photo.id.id))
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