package com.rupp.news_app_demo_android.shared.data.repository

import com.rupp.news_app_demo_android.shared.data.remote.CategoryApiService
import com.rupp.news_app_demo_android.shared.domain.model.Category
import com.rupp.newsapp.core.network.ApiResult
import com.rupp.newsapp.core.network.NetworkUtils
import com.rupp.newsapp.core.network.RetrofitInstance

class CategoryRepository {
    private val api = RetrofitInstance.retrofit.create(CategoryApiService::class.java)

    suspend fun getCategories(): ApiResult<List<Category>> {
        return NetworkUtils.safeApiCall { api.getCategories() }
    }
}