package com.rupp.news_app_demo_android.feature.article.presentation

import com.rupp.news_app_demo_android.shared.domain.model.Article


data class ArticleUiState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val article: Article? = null
)
