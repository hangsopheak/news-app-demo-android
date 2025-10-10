package com.rupp.news_app_demo_android.shared.data.remote

import com.rupp.news_app_demo_android.shared.domain.model.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApiService {
    @GET("articles?_expand=category")
    suspend fun getArticles(
        @Query("categoryId") categoryId: Int? = null,
        @Query("isLatest") isLatest: Boolean? = null,
        @Query("isBookmarked") isBookmarked: Boolean? = null, // Corrected typo
        @Query("isBreaking") isBreaking: Boolean? = null,
        @Query("isFeatured") isFeatured: Boolean? = null
        // Add other potential query parameters here
    ): Response<List<Article>>

    @GET("articles/{articleId}?_expand=category")
    suspend fun getArticleById(@Path("articleId") articleId: Int): Response<Article>

}