package com.rupp.news_app_demo_android.ui.home

import HomeArticleItems
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_5
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rupp.news_app_demo_android.data.HomeData
import com.rupp.news_app_demo_android.model.Article
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme
import com.rupp.news_app_demo_android.utils.ArticleFlagEnum

@Composable
fun NewsSection(
    articleFlagEnum: ArticleFlagEnum,
    articles: List<Article>,
    ) {
    SectionTitleBar(articleFlagEnum.description, onSeeMoreClicked = {  })
    HomeArticleItems(articles)
    Spacer(modifier = Modifier.size(10.dp))
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = PIXEL_5)
fun HomeScreenContent() {

    NewsappdemoandroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewsSection(ArticleFlagEnum.BREAKING_NEWS, HomeData.breakingArticles)
            NewsSection(ArticleFlagEnum.FEATURED_NEWS, HomeData.featuredArticles)
            NewsSection(ArticleFlagEnum.LATEST_NEWS, HomeData.latestArticles)
        }
    }
}