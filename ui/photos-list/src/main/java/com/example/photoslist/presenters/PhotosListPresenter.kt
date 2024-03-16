package com.example.photoslist.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.domain.models.models.Photo
import com.example.domain.models.usecases.GetPhotosUseCase
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.PhotosListEvents.PhotoClicked
import com.example.photoslist.models.toUiPhoto
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList



class PhotosListPresenter (private val getPhotosUseCase: GetPhotosUseCase): Presenter<PhotoListUiState> {
    @Composable
    override fun present(): PhotoListUiState {
        val state: Result<List<Photo>>? by getPhotosUseCase.flow.collectAsState(initial = null)
        val eventSink: (PhotosListEvents) -> Unit = {
            when (it) {
                is PhotoClicked -> {

                }
            }
        }
        return when  {
            state?.isSuccess == true-> PhotoListUiState.Success(
                state!!.getOrNull()?.map(Photo::toUiPhoto).orEmpty().toPersistentList(),
                eventSink = eventSink)
            state?.isFailure == true -> PhotoListUiState.Failure(state?.exceptionOrNull())
            else -> PhotoListUiState.Loading
        }
    }
}