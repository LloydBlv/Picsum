package com.example.photoslist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.models.models.Author
import com.example.domain.models.models.FileName
import com.example.domain.models.models.Id
import com.example.domain.models.models.Size
import com.example.photoslist.composables.PhotosListScreen
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.PhotosListEvents
import com.example.photoslist.models.UiPhoto
import com.slack.circuit.test.TestEventSink
import kotlinx.collections.immutable.toPersistentList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class PhotosListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `when state is loading, progressbar is showing`() {
        composeTestRule.setContent {
            PhotosListScreen(state = PhotoListUiState.Loading)
        }
        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
        composeTestRule.onNodeWithTag("photo_list").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("error").assertIsNotDisplayed()
    }

    @Test
    fun `when state is success, photos list is displayed`() {
        val photos = listOf(
            UiPhoto(
                size = Size(100, 100),
                fileName = FileName("file1"),
                id = Id(1),
                author = Author(name = "author1", url = "")
            ),
            UiPhoto(
                size = Size(100, 100),
                fileName = FileName("file2"),
                id = Id(2),
                author = Author(name = "author2", url = "")
            )
        )
        composeTestRule.setContent {
            PhotosListScreen(state = PhotoListUiState.Success(photos.toPersistentList()))
        }
        composeTestRule.onNodeWithTag("loading").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("photo_list").assertIsDisplayed()
        composeTestRule.onNodeWithTag("photo_item_0").assertIsDisplayed()
        composeTestRule.onNodeWithTag("photo_item_1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("photo_item_2").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error").assertIsNotDisplayed()
    }

    @Test
    fun `when state is success, photos items are clickable`() {
        val photos = listOf(
            UiPhoto(
                size = Size(100, 100),
                fileName = FileName("file1"),
                id = Id(1),
                author = Author(name = "author1", url = "")
            ),
            UiPhoto(
                size = Size(100, 100),
                fileName = FileName("file2"),
                id = Id(2),
                author = Author(name = "author2", url = "")
            )
        )
        val events = TestEventSink<PhotosListEvents>()
        composeTestRule.setContent {
            PhotosListScreen(
                state = PhotoListUiState.Success(
                    photos = photos.toPersistentList(),
                    eventSink = events
                )
            )
        }
        composeTestRule.onNodeWithTag("loading").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("photo_list").assertIsDisplayed()
        composeTestRule.onNodeWithTag("photo_item_0").run {
            assertIsDisplayed()
            performClick()
        }
        events.assertEvents(PhotosListEvents.PhotoClicked(photos[0]))
        composeTestRule.onNodeWithTag("photo_item_1").run {
            assertIsDisplayed()
            performClick()
        }
        events.assertEventAt(1, PhotosListEvents.PhotoClicked(photos[1]))
        composeTestRule.onNodeWithTag("photo_item_2").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error").assertIsNotDisplayed()
    }

    @Test
    fun `when state is error, error message is displayed`() {
        val error = Throwable("error message")
        composeTestRule.setContent {
            PhotosListScreen(state = PhotoListUiState.Failure(error))
        }
        composeTestRule.onNodeWithTag("loading").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("photo_list").assertIsNotDisplayed()
        composeTestRule.onNodeWithText(error.message!!).assertIsDisplayed()
    }

    @Test
    fun `when state is error, retry button is clickable`() {
        val events = TestEventSink<PhotosListEvents>()

        val error = Throwable("error message")
        composeTestRule.setContent {
            PhotosListScreen(state = PhotoListUiState.Failure(error, events))
        }
        composeTestRule.onNodeWithTag("loading").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("photo_list").assertIsNotDisplayed()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.onNodeWithText(context.getString(R.string.retry)).run {
            assertIsDisplayed()
            performClick()
        }
        events.assertEvents(PhotosListEvents.RetryClicked)
    }
}