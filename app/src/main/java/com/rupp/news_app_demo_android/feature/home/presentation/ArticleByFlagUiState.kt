package com.rupp.news_app_demo_android.feature.home.presentation

import com.rupp.news_app_demo_android.shared.domain.model.Article

data class ArticleByFlagUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val articles: List<Article> = emptyList(),
)
