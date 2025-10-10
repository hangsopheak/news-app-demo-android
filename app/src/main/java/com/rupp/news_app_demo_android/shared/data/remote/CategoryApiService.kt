package com.rupp.news_app_demo_android.shared.data.remote

import com.rupp.news_app_demo_android.shared.domain.model.Category
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>
}