package com.rupp.news_app_demo_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rupp.news_app_demo_android.ui.main.MainScreen
import com.rupp.news_app_demo_android.ui.onboarding.OnboardingScreen
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme
import com.rupp.news_app_demo_android.utils.OnboardingUtil

class MainActivity : ComponentActivity() {

    private val onboardingUtil by lazy { OnboardingUtil(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isOnboardingCompleted by remember {
                mutableStateOf(onboardingUtil.isOnboardingCompleted())
            }
            NewsappdemoandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isOnboardingCompleted) {
                        MainScreen()
                    } else {
                        OnboardingScreen(
                            onFinished = {
                                onboardingUtil.setOnboardingCompleted()
                                isOnboardingCompleted = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsappdemoandroidTheme {
        Greeting("Android")
    }
}