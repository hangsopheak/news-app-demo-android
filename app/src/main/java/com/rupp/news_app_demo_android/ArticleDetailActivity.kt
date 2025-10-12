package com.rupp.news_app_demo_android


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rupp.news_app_demo_android.shared.data.local.ArticleData
import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.news_app_demo_android.feature.article.presentation.ArticleScreen
import com.rupp.news_app_demo_android.feature.article.presentation.ArticleViewModel
import com.rupp.news_app_demo_android.shared.data.repository.ArticleRepository
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleDetailActivity : ComponentActivity() {

    private var articleId:Int = 0;
    override fun onStart() {
        super.onStart()
        articleId = intent.getIntExtra("article_id", 0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel : ArticleViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                if (articleId != 0) { // Add safety check
                    viewModel.loadArticleDetail(articleId)
                }
            }

            NewsappdemoandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        state.isLoading -> {
                            // Show loading indicator
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        state.error != null -> {
                            LaunchedEffect(state.error) {
                                Toast.makeText(context, "Error: ${state.error}", Toast.LENGTH_SHORT).show()
                            }
                            // Show error UI instead of just toast
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Error: ${state.error}")
                            }
                        }
                        state.article != null -> {
                            ArticleScreen(
                                article = state.article!!,
                                onBackClick = { finish() }
                            )

                        }
                        else -> {
                            // Handle case where article is null but no error
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No article found")
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun NoArticleFound() {
        Text("No article")
    }
}