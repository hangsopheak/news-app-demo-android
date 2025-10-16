package com.rupp.news_app_demo_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The main [Application] class for the News App.
 *
 * This class is annotated with [@HiltAndroidApp], which triggers Hilt's code generation
 * and creates a dependency container that is attached to the application's lifecycle.
 * It serves as the entry point for the dependency injection framework.
 */
@HiltAndroidApp
class NewsApp : Application() {
}