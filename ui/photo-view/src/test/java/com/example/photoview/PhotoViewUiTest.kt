package com.example.photoview

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.domain.models.models.Size
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [32])
class PhotoViewUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val roborazziRule = RoborazziRule(
        composeRule = composeTestRule,
        captureRoot = composeTestRule.onRoot(),
        options = RoborazziRule.Options(
            outputDirectoryPath = "src/androidUnitTest/snapshots/images",
        ),
    )

    @Test
    fun landscapePhotoViewTest() {
        composeTestRule.setContent {
            PhotoViewScreen(
                state = PhotoViewUiState(
                    size = Size(width = 5000, height = 3333),
                    imageUrl = "https://example.com/image.jpg",
                    authorName = "author1",
                    eventSink = {}
                )
            )
        }
        composeTestRule.onRoot().captureRoboImage()
    }

    @Test
    fun portraitPhotoViewTest() {
        composeTestRule.setContent {
            PhotoViewScreen(
                state = PhotoViewUiState(
                    size = Size(width = 3887, height = 4899),
                    imageUrl = "https://example.com/image.jpg",
                    authorName = "author1",
                    eventSink = {}
                )
            )
        }
        composeTestRule.onRoot().captureRoboImage()
    }
}