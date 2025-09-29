package com.rupp.news_app_demo_android.feature.bookmark.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rupp.news_app_demo_android.ArticleDetailActivity
import com.rupp.news_app_demo_android.shared.data.local.ArticleData
import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.news_app_demo_android.shared.presentation.ArticleCardVertical

@Composable
fun BookMarkScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        val bookMarkedArticles = ArticleData.allArticles.filter { it.isBookMarked }
        items(bookMarkedArticles.size) { index ->
            ArticleCardVertical(article = bookMarkedArticles[index], showBookMark = false, onClick = {
                onClickArticle(context, bookMarkedArticles[index])
            })
        }
    }

}

fun onClickArticle(context: Context,  article: Article) {
    val intent = Intent(context, ArticleDetailActivity::class.java)
    intent.putExtra("article_id", article.id)
    context.startActivity(intent)
}
