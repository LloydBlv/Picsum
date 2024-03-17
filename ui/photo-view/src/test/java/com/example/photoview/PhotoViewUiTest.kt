@file:OptIn(ExperimentalCoilApi::class)

package com.example.photoview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import coil.Coil
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import com.example.domain.models.models.Id
import com.example.domain.models.models.RemotePhoto
import com.example.domain.models.models.Size
import com.example.photoview.composables.PhotoViewScreen
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [32], qualifiers = RobolectricDeviceQualifiers.MediumPhone)
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

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val engine = FakeImageLoaderEngine.Builder()
            .intercept({ it is String && it.startsWith("https://picsum.photos/") }, ColorDrawable(Color.GREEN))
            .default(ColorDrawable(Color.BLUE))
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
        Coil.setImageLoader(imageLoader)
    }

    @Test
    fun landscapePhotoViewTest() {
        composeTestRule.setContent {
            PhotoViewScreen(
                state = PhotoViewUiState(
                    remotePhoto = RemotePhoto(
                        size = Size(width = 5000, height = 3333),
                        id = Id(1),
                    ),
                    authorName = "author1",
                    eventSink = {},
                ),
            )
        }
        composeTestRule.onRoot().captureRoboImage()
    }

    @Test
    fun portraitPhotoViewTest() {
        composeTestRule.setContent {
            PhotoViewScreen(
                state = PhotoViewUiState(
                    remotePhoto = RemotePhoto(
                        size = Size(width = 3887, height = 4899),
                        id = Id(1),
                    ),
                    authorName = "author1",
                    eventSink = {},
                ),
            )
        }
        composeTestRule.onRoot().captureRoboImage()
    }
}
