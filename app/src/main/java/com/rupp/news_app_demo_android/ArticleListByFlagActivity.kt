package com.rupp.news_app_demo_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rupp.news_app_demo_android.shared.data.local.ArticleData
import com.rupp.news_app_demo_android.shared.domain.model.Article
import com.rupp.news_app_demo_android.shared.presentation.ArticleCardVertical
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme
import com.rupp.news_app_demo_android.feature.home.domain.ArticleFlagEnum
import com.rupp.news_app_demo_android.feature.home.presentation.ArticleByFlagViewModel
import com.rupp.news_app_demo_android.shared.data.repository.ArticleRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.get

@AndroidEntryPoint
class ArticleListByFlagActivity : ComponentActivity() {

    var articleFlagId: Int = 0;

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        articleFlagId = intent.getIntExtra("article_flag_id", 0)


        setContent {
            val viewModel : ArticleByFlagViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                if (articleFlagId != 0) { // Add safety check
                    viewModel.loadArticlesByFlag(ArticleFlagEnum.fromId(articleFlagId)!!)
                }
            }

            NewsappdemoandroidTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(ArticleFlagEnum.fromId(articleFlagId)?.description ?: "") },
                            navigationIcon = {
                                IconButton(onClick = { this.finish() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
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
                        else -> {
                            LazyColumn(
                                modifier = Modifier.padding(innerPadding),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                items(state.articles.size) { index ->
                                    ArticleCardVertical(
                                        article = state.articles[index],
                                        showBookMark = false,
                                        onClick = {
                                            onClickArticle(context, state.articles[index])
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun onClickArticle(context: Context, article: Article) {
        val intent = Intent(context, ArticleDetailActivity::class.java)
        intent.putExtra("article_id", article.id)
        context.startActivity(intent)
    }
}