package com.rupp.news_app_demo_android.utils

object ImageUrlHelper {

    // Picsum Photos - Reliable, fast, beautiful images
    fun getPicsumUrl(
        width: Int = 800,
        height: Int = 600,
        seed: Int? = null
    ): String {
        return if (seed != null) {
            "https://picsum.photos/${width}/${height}?random=$seed"
        } else {
            "https://picsum.photos/${width}/${height}"
        }
    }


}
