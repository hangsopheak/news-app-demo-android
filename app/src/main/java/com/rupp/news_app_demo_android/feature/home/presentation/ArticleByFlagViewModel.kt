package com.rupp.news_app_demo_android.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rupp.news_app_demo_android.feature.home.domain.ArticleFlagEnum
import com.rupp.news_app_demo_android.shared.data.repository.ArticleRepository
import com.rupp.newsapp.core.network.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleByFlagViewModel(private val repository: ArticleRepository) : ViewModel()  {

    private val _uiState = MutableStateFlow(ArticleByFlagUiState())
    val uiState: MutableStateFlow<ArticleByFlagUiState> = _uiState

    fun loadArticlesByFlag(articleFlagEnum: ArticleFlagEnum){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val articles = when (articleFlagEnum) {
                ArticleFlagEnum.BREAKING_NEWS -> repository.getBreakingArticles()
                ArticleFlagEnum.FEATURED_NEWS -> repository.getFeaturedArticles()
                ArticleFlagEnum.LATEST_NEWS -> repository.getLatestArticles()
            }

            when (articles) {
                is ApiResult.Success -> {
                    _uiState.value = ArticleByFlagUiState(
                        articles = articles.data,
                        isLoading = false
                    )
                }
                is ApiResult.Error -> {
                    _uiState.value = ArticleByFlagUiState(
                        error = articles.exception.message ?: "Unknown error",
                        isLoading = false
                    )
                }
            }
        }
    }
}