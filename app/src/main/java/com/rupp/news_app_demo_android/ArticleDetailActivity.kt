package com.rupp.news_app_demo_android


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rupp.news_app_demo_android.shared.data.local.ArticleData
import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.news_app_demo_android.feature.article.presentation.ArticleScreen
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme

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
            NewsappdemoandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val article: Article? = ArticleData.allArticles.find { it.id == articleId }
                    if (article != null) {
                        ArticleScreen(article = article, onBackClick = { finish() })
                    } else {
                        NoArticleFound()
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