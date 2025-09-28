package com.rupp.news_app_demo_android.ui.explore.presentation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rupp.news_app_demo_android.data.ArticleData
import com.rupp.news_app_demo_android.model.Article
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme


@Composable
fun ArticleCardVertical(article: Article, showBookMark: Boolean = true, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment =Alignment.Top , horizontalArrangement = Arrangement.SpaceBetween) {
            ArticleTitleSection(article = article, titleFontSize = 16, showBookMark = showBookMark, modifier = Modifier.weight(1f).padding(end = 8.dp))
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                modifier = Modifier
                    .width(130.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ArticleCardVerticalPreview() {
    NewsappdemoandroidTheme {
        ArticleCardVertical(article = ArticleData.allArticles[2], onClick = {})
    }
}