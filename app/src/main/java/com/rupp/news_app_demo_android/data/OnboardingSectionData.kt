package com.rupp.news_app_demo_android.data

import com.rupp.news_app_demo_android.R
import com.rupp.news_app_demo_android.model.OnboardingSection

object OnboardingSectionData {
    fun getMainOnboardingSections(): List<OnboardingSection> = listOf(
        // =============================================================================
        // SECTION 1: Stay Informed & Updated
        // =============================================================================
        OnboardingSection(
            title = "Stay Informed",
            subtitle = "Breaking News at Your Fingertips",
            description = "Get the latest breaking news, trending stories, and real-time updates from trusted sources around the world. Never miss what matters most to you.",
            imageRes = R.drawable.onboarding_section1
        ),

        // =============================================================================
        // SECTION 2: Personalized Experience
        // =============================================================================
        OnboardingSection(
            title = "Your News, Your Way",
            subtitle = "Personalized Content Just for You",
            description = "Choose from multiple categories like Technology, Sports, Business, Entertainment, and Health. Get news recommendations tailored to your interests and reading habits.",
            imageRes = R.drawable.onboarding_section2
        ),

        // =============================================================================
        // SECTION 3: Save & Share
        // =============================================================================
        OnboardingSection(
            title = "Save & Share",
            subtitle = "Bookmark and Connect",
            description = "Bookmark your favorite articles to read later, even offline. Share interesting stories with friends and family across all your social platforms with just one tap.",
            imageRes = R.drawable.onboarding_section3
        )
    )
}