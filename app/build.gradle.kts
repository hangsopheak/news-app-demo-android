plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //The Hilt Gradle Plugin is the invisible helper that does all the setup work
    // behind the scenes. It's not a library you use in your code; it's a build tool
    // that looks at your Hilt annotations and automatically generates and connects all
    // the complex dependency injection code for the Android framework.
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.rupp.news_app_demo_android"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rupp.news_app_demo_android"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_BASE_URL", "\"${project.findProperty("API_BASE_URL")}\"")
        buildConfigField("String", "DB_NAME", "\"${project.findProperty("DB_NAME")}\"")
    }



    buildTypes {
        debug {
            // Debug version points to dev/staging environment
            buildConfigField("String", "API_BASE_URL", "\"${project.findProperty("API_BASE_URL")}\"")
            buildConfigField("String", "DB_NAME", "\"${project.findProperty("DB_NAME")}\"")

            // Helpful for debugging
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }

        release {
            // Release version points to production
            buildConfigField("String", "API_BASE_URL", "\"${project.findProperty("API_BASE_URL_PROD")}\"")
            buildConfigField("String", "DB_NAME", "\"${project.findProperty("DB_NAME_PROD")}\"")

            //isMinifyEnabled = true is a setting in your Android app's build.gradle.kts
            // file that enables tools to make your app smaller and more secure for a release build. It activate
            // 1. Code Shrinking (Minification)
            // 2. Code Obfuscation
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        android.buildFeatures.buildConfig = true
    }

    flavorDimensions += "edition"
    productFlavors {
        create("free") {
            dimension = "edition"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"

            // Free edition config
            buildConfigField("Boolean", "IS_PREMIUM", "false")
        }

        create("premium") {
            dimension = "edition"
            applicationIdSuffix = ".premium"
            versionNameSuffix = "-premium"

            // Premium edition config
            buildConfigField("Boolean", "IS_PREMIUM", "true")
        }
    }
}

dependencies {

    implementation(libs.androidx.junit.ktx)
    // Instrumented + Compose UI Test
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose UI Test
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.7")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.7")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.1")
    kapt("com.google.dagger:hilt-android-compiler:2.57.1")
    // Hilt + Compose integration
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}