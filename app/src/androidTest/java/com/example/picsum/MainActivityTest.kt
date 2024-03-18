package com.example.picsum

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.PhotoRepositoryModule
import com.example.domain.models.repositories.PhotoRepository
import com.example.testing.PhotoRepositoryFake
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import java.net.SocketTimeoutException
import javax.inject.Inject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(PhotoRepositoryModule::class)
@HiltAndroidTest
class MainActivityTest {

    @BindValue
    @JvmField
    val fakeRepository: PhotoRepository = PhotoRepositoryFake(
        context = ApplicationProvider.getApplicationContext(),
    )

    @get:Rule val robotTestRule = RobotTestRule(this)

    @Inject
    lateinit var aboutScreenRobot: MainActivityRobot

    @Test
    fun testGridIsDisplayedAndSwitchingWorks() {
        launch(MainActivity::class.java)
        aboutScreenRobot(robotTestRule) { assertFullFlow() }
    }

    @Test
    fun testRetryFlowWorks() {
        (fakeRepository as PhotoRepositoryFake).exception = SocketTimeoutException()
        launch(MainActivity::class.java)
        aboutScreenRobot(robotTestRule) {
            assertRetryButtonIdDisplayed()
            assertErrorViewIsDisplayed()
            fakeRepository.exception = null
            clickRetry()
            assertFullFlow()
        }
    }

    @Test
    fun activityRecreationPreservesGridState() {
        val scenario = launch(MainActivity::class.java)
        aboutScreenRobot(robotTestRule) {
            assertFirstThreePhotoItemsDisplayed()
            scrollGridTo(1000)
            scenario.recreate()
            assertPhotoWithIdDisplayed(1000)
            assertGridDisplayed()
        }
    }

    @Test
    fun activityRecreationPreservesListState() {
        val scenario = launch(MainActivity::class.java)
        aboutScreenRobot(robotTestRule) {
            clickToggleButton()
            assertFirstThreePhotoItemsDisplayed()
            scrollListTo(1000)
            scenario.recreate()
            assertPhotoWithIdDisplayed(1000)
            assertListViewDisplayed()
        }
    }

    @Test
    fun activityRecreationInPhotoViewPreservesListState() {
        val scenario = launch(MainActivity::class.java)
        aboutScreenRobot(robotTestRule) {
            clickToggleButton()
            assertFirstThreePhotoItemsDisplayed()
            scrollListTo(1000)
            clickOnPhotoItemWithId(1000)
            assertPhotoViewAppBarIdDisplayed()
            assertAuthorNameIsDisplayed("Lukas Budimaier")
            scenario.recreate()
            Espresso.pressBack()
            assertPhotoWithIdDisplayed(1000)
            assertListViewDisplayed()
        }
    }
}
