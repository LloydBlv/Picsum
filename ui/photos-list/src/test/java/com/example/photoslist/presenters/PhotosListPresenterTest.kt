package com.example.photoslist.presenters

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import com.example.domain.models.usecases.GetPhotosUseCase
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.screens.PhotosListScreen
import com.example.screens.PhotoViewScreen
import com.example.testing.PhotoRepositoryFake
import com.slack.circuit.test.FakeNavigator
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
        val presenter = PhotosListPresenter(getPhotosUseCase, FakeNavigator(PhotosListScreen))
        presenter.test {
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual == PhotoListUiState.Loading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `state is success when use case returns photos`() = runTest {
        val getPhotosUseCase = GetPhotosUseCase(PhotoRepositoryFake())
        val presenter = PhotosListPresenter(getPhotosUseCase, FakeNavigator(PhotosListScreen))
        presenter.test {
            skipItems(1)
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual is PhotoListUiState.Success)
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `when state is success, clicking photos navigates to view photo screen`() = runTest {
        val getPhotosUseCase = GetPhotosUseCase(PhotoRepositoryFake())
        val navigator = FakeNavigator(PhotosListScreen)
        val presenter = PhotosListPresenter(getPhotosUseCase, navigator)
        presenter.test {
            skipItems(1)
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual is PhotoListUiState.Success)
            val photo = (actual as PhotoListUiState.Success).photos.first()
            actual.eventSink.invoke(PhotosListEvents.PhotoClicked(photo))
            assertThat(navigator.awaitNextScreen()).isInstanceOf(PhotoViewScreen::class)
            navigator.expectNoEvents()
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `state is failure when use case returns error`() = runTest {
        val repository = PhotoRepositoryFake()
        val socketTimeoutException = SocketTimeoutException("timeout")
        repository.exception = socketTimeoutException
        val getPhotosUseCase = GetPhotosUseCase(repository)
        val presenter = PhotosListPresenter(getPhotosUseCase, FakeNavigator(PhotosListScreen))
        presenter.test {
            skipItems(1)
            val actual: PhotoListUiState = awaitItem()
            assertThat(actual is PhotoListUiState.Failure)
            assertThat(actual).transform { it as PhotoListUiState.Failure }
                .prop(PhotoListUiState.Failure::error).isEqualTo(socketTimeoutException)
            ensureAllEventsConsumed()
        }
    }
}