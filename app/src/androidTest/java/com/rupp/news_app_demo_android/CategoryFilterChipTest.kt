package com.rupp.news_app_demo_android

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rupp.news_app_demo_android.feature.explore.presentation.CategoryFilterChips
import com.rupp.news_app_demo_android.shared.domain.model.Category
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryFilterChipsTest
{
    @get:Rule
    val composeTestRule = createComposeRule()

    private val categories = listOf(
        Category(1, "Science"),
        Category(2, "Technology"),
        Category(3, "Sports")
    )

    @Test
    fun allCategoriesAreDisplayed() {
        composeTestRule.setContent {
            CategoryFilterChips(
                categories = categories,
                selectedCategoryId = null,
                onCategorySelected = {}
            )
        }

        // Assert all category labels are shown
        categories.forEach {
            composeTestRule.onNodeWithText(it.name).assertIsDisplayed()
        }
    }

    @Test
    fun clickingChipCallsCallback() {
        var selectedId: Int? = null

        composeTestRule.setContent {
            CategoryFilterChips(
                categories = categories,
                selectedCategoryId = null,
                onCategorySelected = { selectedId = it }
            )
        }

        // Click "Technology"
        composeTestRule.onNodeWithText("Technology").performClick()

        // Assert callback fired with id=2
        assertEquals(2, selectedId)
    }

    /**
     * Verifies that the initially selected category chip is correctly highlighted
     * (marked as selected) while the other chips are not. It sets the "Sports"
     * category as selected and then asserts that its corresponding chip has the
     * 'selected' state, while the "Science" and "Technology" chips do not.
     */
    @Test
    fun selectedChipIsHighlighted() {
        composeTestRule.setContent {
            CategoryFilterChips(
                categories = categories,
                selectedCategoryId = 3, // Sports selected
                onCategorySelected = {}
            )
        }

        // Check selected state (Sports is selected)
        composeTestRule.onNodeWithText("Sports")
            .assertIsDisplayed()
            .assertIsSelected()
            .assertHasClickAction()
        composeTestRule.onNodeWithText("Science").assertIsNotSelected()
        composeTestRule.onNodeWithText("Technology").assertIsNotSelected()
    }

}