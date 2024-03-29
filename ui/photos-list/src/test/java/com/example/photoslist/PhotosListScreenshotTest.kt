package com.example.photoslist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import coil.Coil
import coil.ImageLoader
import coil.test.FakeImageLoaderEngine
import com.example.domain.models.models.Author
import com.example.domain.models.models.FileName
import com.example.domain.models.models.Id
import com.example.domain.models.models.Photo
import com.example.domain.models.models.RemoteThumbnail
import com.example.domain.models.models.Size
import com.example.photoslist.composables.PhotoItem
import com.example.photoslist.composables.PhotosListScreen
import com.example.photoslist.composables.PhotosListScreenContent
import com.example.photoslist.models.PhotoListUiState
import com.example.photoslist.models.UiPhoto
import com.example.photoslist.models.toUiPhoto
import com.example.testing.PhotoRepositoryFake
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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
class PhotosListScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val roborazziRule = RoborazziRule(
        composeRule = composeRule,
        captureRoot = composeRule.onRoot(),
        options = RoborazziRule.Options(
            outputDirectoryPath = "src/androidUnitTest/snapshots/images",
        ),
    )

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(
                { it is String && it.startsWith("https://picsum.photos/") },
                ColorDrawable(
                    Color.GREEN,
                ),
            )
            .default(ColorDrawable(Color.BLUE))
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
        Coil.setImageLoader(imageLoader)
    }

    @Test
    fun loadingStateTest() {
        composeRule.mainClock.autoAdvance = false
        composeRule.setContent {
            PhotosListScreen(
                state = PhotoListUiState.Loading,
                modifier = Modifier.fillMaxSize(),
            )
        }
        composeRule.mainClock.advanceTimeBy(500)
        captureRoot()
    }

    @Test
    fun successTextStateTest() {
        val repository = PhotoRepositoryFake()
        val photos = runBlocking {
            repository.getPhotos().first().getOrNull()!!.map(Photo::toUiPhoto).toPersistentList()
        }
        composeRule.setContent {
            PhotosListScreen(
                state = PhotoListUiState.Success(
                    photos = photos,
                    showStaggeredView = false,
                ),
                modifier = Modifier.fillMaxSize(),
            )
        }
        captureRoot()
    }

    @Test
    fun successGridStateTest() {
        val repository = PhotoRepositoryFake()
        val photos = runBlocking {
            repository.getPhotos()
                .first()
                .getOrNull()!!
                .map(Photo::toUiPhoto)
                .reversed()
                .toPersistentList()
        }
        composeRule.setContent {
            PhotosListScreen(
                state = PhotoListUiState.Success(
                    photos = photos,
                    showStaggeredView = true,
                ),
                modifier = Modifier.fillMaxSize(),
            )
        }
        captureRoot()
    }

    @Test
    fun failedStateTest() {
        composeRule.setContent {
            PhotosListScreenContent(
                state = PhotoListUiState.Failure("Failed"),
                eventSink = {},
            )
        }
        captureRoot()
    }

    @Test
    fun photoItemTest() {
        val testUiPhoto = UiPhoto(
            size = Size(100, 100),
            fileName = FileName("File name test 1234.jpg"),
            id = Id(0),
            author = Author("Test", "Test"),
            remoteThumbnail = RemoteThumbnail(
                id = Id(0),
                size = Size(100, 100),
            ),
        )
        composeRule.setContent {
            PhotoItem(
                photo = testUiPhoto,
                modifier = Modifier.wrapContentSize(),
                eventSink = {},
            )
        }
        captureRoot()
    }

    private fun captureRoot() {
        composeRule.onRoot().captureRoboImage()
    }
}
