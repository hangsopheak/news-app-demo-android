package com.rupp.news_app_demo_android.ui.article

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rupp.news_app_demo_android.data.CategoryData.categories
import com.rupp.news_app_demo_android.data.randomPublishedAt
import com.rupp.news_app_demo_android.model.Article
import com.rupp.news_app_demo_android.ui.explore.presentation.ArticleTitleSection
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme
import com.rupp.news_app_demo_android.utils.ImageUrlHelper

@Composable
fun ArticleScreen(modifier: Modifier = Modifier,
                  article: Article,
                  onBackClick: () -> Unit = {}
) {

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        // Hero Image with Overlay
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                // Hero Image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = article.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.6f)
                                )
                            )
                        )
                )

                // Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .statusBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Back Button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .background(
                                Color.Black.copy(alpha = 0.2f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    // Category Badge
                    Surface (
                        modifier = Modifier,
                        shape = RoundedCornerShape(16.dp),
                        color = Color.Black.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = article.category.name,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Content Container
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-24).dp),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column (
                    modifier = Modifier.padding(24.dp)
                ) {
                    ArticleTitleSection(article = article)
                    Spacer(Modifier.size(24.dp))

                    // Article Content
                    val spanned = remember(article.content) {
                        HtmlCompat.fromHtml(article.content, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    }

                    AndroidView(
                        factory = { context ->
                            TextView(context).apply {
                                setTextColor(android.graphics.Color.BLACK)
                                textSize = 16f
                            }
                        },
                        update = { textView ->
                            textView.text = spanned
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ArticleScreenPreview() {
    NewsappdemoandroidTheme {
        ArticleScreen(article = Article(id = 322, categoryId = 8, title = "Ancient City Uncovered in Amazon Rainforest",
            content = """
                <!DOCTYPE html><html lang="en"><body style="margin:0;padding:15px;font-family:Arial,sans-serif;background-color:#f5f5f5;color:#333;line-height:1.2"><div style="max-width:600px;margin:0 auto;background:white;padding:20px;border-radius:8px;box-shadow:0 2px 5px rgba(0,0,0,0.1)"><p>Researchers have discovered a vast ancient city hidden deep within the Amazon rainforest using advanced satellite imaging technology.</p><div style="background:#e8f4e8;padding:15px;border-radius:6px;margin:15px 0"><p style="margin:0;font-style:italic">"This discovery completely changes our understanding of pre-Columbian history in the Amazon."</p><p style="margin:5px 0 0 0;text-align:right;font-weight:bold">- Dr. Elena Torres, Lead Archaeologist</p></div><h2 style="color:#2c5f2d;font-size:20px;margin-top:25px">Key Findings</h2><ul style="padding-left:20px"><li>Advanced road networks connecting various city sections</li><li>Sophisticated water management systems</li><li>Agricultural terraces showing advanced farming</li><li>Residential areas for thousands of inhabitants</li></ul><h2 style="color:#2c5f2d;font-size:20px;margin-top:25px">Discovery Timeline</h2><p><strong>2018:</strong> Initial satellite surveys identify unusual patterns</p><p><strong>2020:</strong> LIDAR technology confirms artificial structures</p><p><strong>2023:</strong> Ground verification begins</p><h2 style="color:#2c5f2d;font-size:20px;margin-top:25px">Significance</h2><p>This discovery challenges the notion that the Amazon was sparsely populated by nomadic tribes. Instead, it reveals complex societies that shaped their environment in sustainable ways.</p><p style="border-top:1px solid #ddd;padding-top:15px;margin-top:25px;font-size:14px;color:#777">© 2023 Archaeological Discovery Journal. This content is for educational purposes.</p></div></body></html>
            """.trimIndent(),
            imageUrl = ImageUrlHelper.getPicsumUrl(640, 480, 61), author = "Archaeology Now", publishedAt = randomPublishedAt(180), category = categories[7]))
    }
}