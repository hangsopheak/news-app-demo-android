package com.rupp.news_app_demo_android.shared.data.repository

import com.rupp.news_app_demo_android.shared.data.remote.ArticleApiService
import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.newsapp.core.network.ApiResult
import com.rupp.newsapp.core.network.NetworkUtils
import com.rupp.newsapp.core.network.RetrofitInstance

class ArticleRepository {
    private val api = RetrofitInstance.retrofit.create(ArticleApiService::class.java)

    suspend fun getLatestArticles(): ApiResult<List<Article>> {
        return NetworkUtils.safeApiCall { api.getArticles(isLatest = true) }
    }

    suspend fun getAllArticles(): ApiResult<List<Article>> {
        return NetworkUtils.safeApiCall { api.getArticles() }
    }

    suspend fun getBookmarkedArticles(): ApiResult<List<Article>> {
        return NetworkUtils.safeApiCall { api.getArticles(isBookmarked = true) }
    }

    suspend fun getBreakingArticles(): ApiResult<List<Article>> {
        return NetworkUtils.safeApiCall { api.getArticles(isBreaking = true) }
    }

    suspend fun getFeaturedArticles(): ApiResult<List<Article>> {
        return NetworkUtils.safeApiCall { api.getArticles(isFeatured = true) }
    }

    suspend fun getArticlesByCategory(categoryId: Int): ApiResult<List<Article>> {
        return NetworkUtils.safeApiCall { api.getArticles(categoryId = categoryId) }
    }

    suspend fun getArticleById(articleId: Int): ApiResult<Article> {
        return NetworkUtils.safeApiCall { api.getArticleById(articleId) }
    }
}