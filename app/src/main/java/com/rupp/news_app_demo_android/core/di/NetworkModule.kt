package com.rupp.news_app_demo_android.core.di

import com.rupp.news_app_demo_android.shared.data.remote.ArticleApiService
import com.rupp.news_app_demo_android.shared.data.remote.CategoryApiService
import com.rupp.newsapp.core.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

/**
 * Dagger Hilt module that provides network-related dependencies for the application.
 *
 * This module is responsible for creating and providing instances of API services
 * (like [ArticleApiService] and [CategoryApiService]) using Retrofit. These services
 * are then available for injection throughout the application, scoped as singletons
 * to ensure a single instance is used.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideArticleApiService(): ArticleApiService =
        RetrofitInstance.retrofit.create(ArticleApiService::class.java)

    @Provides
    @Singleton
    fun provideCategoryApiService(): CategoryApiService =
        RetrofitInstance.retrofit.create(CategoryApiService::class.java)

}