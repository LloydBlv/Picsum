package com.example.photoslist.presenters

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import com.example.domain.models.usecases.GetPhotosUseCase
import com.example.photoslist.models.PhotoListUiState
import com.example.testing.PhotoRepositoryFake
import com.slack.circuit.test.test
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.net.SocketTimeoutException

@RunWith(RobolectricTestRunner::class)
class PhotosListPresenterTest {
    @Test
    fun `initially state is loading`() = runTest {
        val getPhotosUseCase = GetPhotosUseCase(PhotoRepositoryFake())
        val presenter = PhotosListPresenter(getPhotosUseCase)
        presenter.test {
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual == PhotoListUiState.Loading)
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `state is success when use case returns photos`() = runTest {
        val getPhotosUseCase = GetPhotosUseCase(PhotoRepositoryFake())
        val presenter = PhotosListPresenter(getPhotosUseCase)
        presenter.test {
            getPhotosUseCase.invoke(Unit)
            skipItems(1)
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual is PhotoListUiState.Success)
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `state is failure when use case returns error`() = runTest {
        val repository = PhotoRepositoryFake()
        val socketTimeoutException = SocketTimeoutException("timeout")
        repository.exception = socketTimeoutException
        val getPhotosUseCase = GetPhotosUseCase(repository)
        val presenter = PhotosListPresenter(getPhotosUseCase)
        presenter.test {
            getPhotosUseCase.invoke(Unit)
            skipItems(1)
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual is PhotoListUiState.Failure)
            assertThat(actual).transform { it as PhotoListUiState.Failure }
                .prop(PhotoListUiState.Failure::error).isEqualTo(socketTimeoutException)
            ensureAllEventsConsumed()
        }
    }
}