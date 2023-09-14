package com.example.giphysearch

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.giphysearch.domain.Gif
import com.example.giphysearch.ui.GiphySearchUiState
import com.example.giphysearch.ui.screens.ResultScreen
import org.junit.Rule
import org.junit.Test

class ResultScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeSuccessUiState = GiphySearchUiState.Success(
        listOf(
            Gif(
                id = "first",
                title = "fake",
                webp = "url"
            )
        )
    )
    private val fakeErrorUiState = GiphySearchUiState.Error(errorText = "error")
    private val fakeBlankUiState = GiphySearchUiState.Blank(infoText = R.string.blank_screen_text)

    @Test
    fun successScreen_showGifs_verifyShown() {
        composeTestRule.setContent {
            ResultScreen(fakeSuccessUiState, {})
        }
        composeTestRule.onNodeWithTag("GifCard").assertExists()
    }

    @Test
    fun errorScreen_showError_verifyShown() {
        composeTestRule.setContent {
            ResultScreen(fakeErrorUiState, {})
        }
        composeTestRule.onNodeWithText("error").assertIsDisplayed()
    }

    @Test
    fun blankScreen_exists_verifyIsBlank() {
        composeTestRule.setContent {
            ResultScreen(fakeBlankUiState, {})
        }
        val text =
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.blank_screen_text)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

}
