package com.example.photoview

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.prop
import com.example.domain.models.models.Size
import com.example.screens.PhotoViewScreen
import com.example.screens.PhotosListScreen
import com.slack.circuit.test.FakeNavigator
import com.slack.circuit.test.test
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class PhotoViewPresenterTest {
    @Test
    fun `test presenter emits state correctly`() = runTest {
        val screen = PhotoViewScreen(
            id = 1,
            authorName = "John Doe",
            width = 123,
            height = 456
        )
        val navigator = FakeNavigator(screen)
        val presenter = PhotoViewPresenter(
            screen = screen,
            navigator
        )
        presenter.test {
            assertThat(awaitItem()).all {
                prop(PhotoViewUiState::authorName).isEqualTo("John Doe")
                prop(PhotoViewUiState::imageUrl).isEqualTo("https://picsum.photos/123/456?image=1")
                prop(PhotoViewUiState::size).isEqualTo(Size(123, 456))
            }
        }
    }

    @Test
    fun `test back press navigates up`() = runTest {
        val screen = PhotoViewScreen(
            id = 1,
            authorName = "John Doe",
            width = 123,
            height = 456
        )
        val navigator = FakeNavigator(PhotosListScreen)
        val presenter = PhotoViewPresenter(
            screen = screen,
            navigator = navigator
        )
        presenter.test {
            val state = awaitItem()
            state.eventSink.invoke(PhotoViewEvents.OnBackPressed)
            navigator.awaitPop()
            assertThat(navigator.peek()).isEqualTo(PhotosListScreen)
            navigator.expectNoEvents()
        }
    }
}