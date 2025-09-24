package com.rupp.news_app_demo_android.model

import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

data class Article(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val content: String,
    val imageUrl: String,
    val author: String?,
    val publishedAt: Date = Date.from(Instant.now().minus((1..100).random().toLong(), ChronoUnit.HOURS)),
    val category: Category,
    val isBreaking: Boolean = false,
    val isFeatured: Boolean = false,
    val isLatest: Boolean = false,
    val isBookMarked : Boolean = false,

    )
