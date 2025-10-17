package com.rupp.news_app_demo_android.feature.article.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rupp.news_app_demo_android.shared.data.repository.ArticleRepository
import com.rupp.newsapp.core.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel()  {

    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState: MutableStateFlow<ArticleUiState> = _uiState

    fun loadArticleDetail(id: Int){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = repository.getArticleById(id)) {
                is ApiResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        article = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is ApiResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}