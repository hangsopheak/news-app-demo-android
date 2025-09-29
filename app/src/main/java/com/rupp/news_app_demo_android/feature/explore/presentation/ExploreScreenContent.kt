package com.rupp.news_app_demo_android.feature.explore.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rupp.news_app_demo_android.ArticleDetailActivity
import com.rupp.news_app_demo_android.shared.data.local.ArticleData
import com.rupp.news_app_demo_android.shared.data.local.CategoryData
import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.news_app_demo_android.shared.presentation.ArticleCardVertical
import kotlin.collections.get

@Composable
@Preview
fun ExploreScreenContent() {


    val context = LocalContext.current
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
    // This derived state will automatically update when selectedCategoryId changes
    val filteredArticles by remember(selectedCategoryId) {
        mutableStateOf(
            if (selectedCategoryId != null) {
                // If a category is selected, get its articles.
                // Provide an empty list as a fallback if the ID is invalid.
                ArticleData.articlesByCategory[selectedCategoryId] ?: emptyList()
            } else {
                // If no category is selected, show all articles.
                ArticleData.allArticles
            }
        )
    }
    // Articles for selected category

    Column {
        CategoryFilterChips(
            categories = CategoryData.categories,
            selectedCategoryId = selectedCategoryId,
            onCategorySelected = { selectedCategoryId = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                val first = filteredArticles.first()
                HeroArticleCard(
                    article = first,
                    onClick = { onClickArticle(context, first) }
                )
            }
            // Remaining articles
            items(filteredArticles.drop(1)) { article ->
                ArticleCardVertical(
                    article = article,
                    showBookMark = false,
                    onClick = { onClickArticle(context, article) }
                )
            }
        }
    }
}

private fun onClickArticle(
    context: Context,
    article: Article
) {
    val intent = Intent(context, ArticleDetailActivity::class.java)
    intent.putExtra("article_id", article.id)
    context.startActivity(intent)
}