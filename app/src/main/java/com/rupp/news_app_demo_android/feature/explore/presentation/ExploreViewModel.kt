package com.rupp.news_app_demo_android.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rupp.news_app_demo_android.shared.data.repository.ArticleRepository
import com.rupp.news_app_demo_android.shared.data.repository.CategoryRepository
import com.rupp.newsapp.core.network.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ExploreViewModel(private val categoryRepository: CategoryRepository, private val articleRepository: ArticleRepository) : ViewModel()  {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: MutableStateFlow<ExploreUiState> = _uiState

    init {
        loadCategories()       // load once
        loadArticlesByCategory(null)     // load all initially
    }

    fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                categoriesState = _uiState.value.categoriesState.copy(isLoading = true, error = null)
            )

            when (val result = categoryRepository.getCategories()) {
                is ApiResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        categoriesState = _uiState.value.categoriesState.copy(
                            isLoading = false,
                            categories = result.data
                        )
                    )
                }
                is ApiResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        categoriesState = _uiState.value.categoriesState.copy(
                            isLoading = false,
                            error = result.exception.message
                        )
                    )
                }
            }
        }
    }

    fun loadArticlesByCategory(categoryId: Int?) {

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                articlesState = _uiState.value.articlesState.copy(isLoading = true, error = null)
            )

            val result = if (categoryId == null) {
                articleRepository.getAllArticles()
            } else {
                articleRepository.getArticlesByCategory(categoryId)
            }

            if(categoryId!=null){
                _uiState.value = _uiState.value.copy(
                    categoriesState = _uiState.value.categoriesState.copy(
                        selectedCategoryId = categoryId
                    )
                )
            }

            try {
                when (result) {
                    is ApiResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            articlesState = _uiState.value.articlesState.copy(
                                isLoading = false,
                                articles = result.data
                            )
                        )
                    }
                    is ApiResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            articlesState = _uiState.value.articlesState.copy(
                                isLoading = false,
                                error = result.exception.message
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    articlesState = _uiState.value.articlesState.copy(
                        isLoading = false,
                        error = e.message
                    )
                )

            }

        }
    }

}