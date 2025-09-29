package com.rupp.news_app_demo_android.feature.home.domain

enum class ArticleFlagEnum(val id: Int, val description: String) {
    BREAKING_NEWS(1, "Breaking News"),
    FEATURED_NEWS(2, "Featured News"),
    LATEST_NEWS(3, "Latest News");
    companion object {
        // Convert int to enum
        fun fromId(id: Int): ArticleFlagEnum? = values().find { it.id == id }
    }
}