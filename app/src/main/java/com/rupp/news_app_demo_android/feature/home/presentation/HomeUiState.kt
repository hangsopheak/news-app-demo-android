package com.rupp.news_app_demo_android.feature.home.presentation

import com.rupp.news_app_demo_android.shared.domain.model.Article

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val breakingArticles: List<Article> = emptyList(),
    val featuredArticles: List<Article> = emptyList(),
    val latestArticles: List<Article> = emptyList(),
)