package com.example.picsum

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.test.espresso.Espresso
import javax.inject.Inject

class MainActivityRobot @Inject constructor() {

    context (RobotTestRule)
    fun assertRetryButtonIdDisplayed() {
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
    context (RobotTestRule)
    fun clickRetry() {
        composeTestRule.onNodeWithText("Retry").performClick()
    }
    context (RobotTestRule)
    fun assertErrorViewIsDisplayed() {
        composeTestRule.onNodeWithText("Something went wrong").assertIsDisplayed()
    }
    context (RobotTestRule)
    fun assertFirstThreePhotoItemsDisplayed() {
        assertPhotoWithIdDisplayed(0)
        assertPhotoWithIdDisplayed(1)
        assertPhotoWithIdDisplayed(2)
    }

    context (RobotTestRule)
    fun assertPhotoWithIdDisplayed(
        id: Int,
    ) {
        composeTestRule.onNodeWithText("$id.jpeg").assertIsDisplayed()
    }
    context (RobotTestRule)
    fun clickOnPhotoItemWithId(
        id: Int,
    ) {
        composeTestRule.onNodeWithText("$id.jpeg").performClick()
    }
    context (RobotTestRule)
    fun assertAuthorNameIsDisplayed(
        name: String,
    ) {
        composeTestRule.onNodeWithText(name).assertIsDisplayed()
    }

    context (RobotTestRule)
    fun assertPhotoViewAppBarIdDisplayed() {
        composeTestRule.onNodeWithText("Photo View").assertIsDisplayed()
    }

    context (RobotTestRule)
    fun scrollGridTo(
        id: Int,
    ) {
        composeTestRule.onNodeWithTag("photo_grid").performScrollToKey(key = id)
    }
    context (RobotTestRule)
    fun scrollListTo(
        id: Int,
    ) {
        composeTestRule.onNodeWithTag("photo_list").performScrollToKey(key = id)
    }
    context (RobotTestRule)
    fun scrollGridToTop() {
        scrollGridTo(0)
    }

    context (RobotTestRule)
    fun clickToggleButton() {
        composeTestRule.onNodeWithTag("toggle_view_button").performClick()
    }

    context (RobotTestRule)
    fun assertGridNotDisplayed() {
        composeTestRule.onNodeWithTag("photo_grid").assertIsNotDisplayed()
    }
    context (RobotTestRule)
    fun assertGridDisplayed() {
        composeTestRule.onNodeWithTag("photo_grid").assertIsDisplayed()
    }
    context (RobotTestRule)
    fun assertListViewDisplayed() {
        composeTestRule.onNodeWithTag("photo_list").assertIsDisplayed()
    }

    context (RobotTestRule)
    fun assertFullFlow() {
        assertFirstThreePhotoItemsDisplayed()
        scrollGridTo(500)
        assertPhotoWithIdDisplayed(500)
        clickOnPhotoItemWithId(500)
        assertAuthorNameIsDisplayed("Greg Shield")
        assertPhotoViewAppBarIdDisplayed()
        Espresso.pressBack()

        // Assert scroll state is preserved
        assertPhotoWithIdDisplayed(500)
        assertPhotoWithIdDisplayed(501)

        scrollGridToTop()
        assertFirstThreePhotoItemsDisplayed()

        // Change view to list
        clickToggleButton()
        assertGridNotDisplayed()
        assertListViewDisplayed()

        assertFirstThreePhotoItemsDisplayed()
        scrollListTo(500)
        assertPhotoWithIdDisplayed(500)
        assertPhotoWithIdDisplayed(501)
        clickOnPhotoItemWithId(500)
        assertAuthorNameIsDisplayed("Greg Shield")
        assertPhotoViewAppBarIdDisplayed()
        Espresso.pressBack()

        // Assert scroll state is preserved
        assertPhotoWithIdDisplayed(500)
        assertPhotoWithIdDisplayed(501)
    }

    operator fun invoke(
        robotTestRule: RobotTestRule,
        function: context(RobotTestRule)
        MainActivityRobot.() -> Unit,
    ) {
        function(robotTestRule, this@MainActivityRobot)
    }
}
