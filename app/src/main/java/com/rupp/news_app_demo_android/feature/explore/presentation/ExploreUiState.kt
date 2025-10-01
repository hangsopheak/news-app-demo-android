package com.rupp.news_app_demo_android.feature.explore.presentation

import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.news_app_demo_android.shared.domain.model.Category


data class ExploreUiState(
    val categoriesState: CategoriesUiState = CategoriesUiState(),
    val articlesState: ArticlesUiState = ArticlesUiState()
)


data class CategoriesUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categories: List<Category> = emptyList(),
    val selectedCategoryId: Int? = null
)

data class ArticlesUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val articles: List<Article> = emptyList()
)