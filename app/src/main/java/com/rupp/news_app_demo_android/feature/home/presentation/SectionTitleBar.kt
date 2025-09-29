package com.rupp.news_app_demo_android.feature.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rupp.news_app_demo_android.ui.theme.NewsappdemoandroidTheme

@Composable
fun SectionTitleBar(title : String, onSeeMoreClicked : () -> Unit = {}){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
        Box(modifier = Modifier.clickable(onClick = onSeeMoreClicked)) {
            Text(
                text = "See More",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0XFFFFFF)
@Composable
fun SectionTitleBarPreview(){
    NewsappdemoandroidTheme {
        SectionTitleBar("Breaking News")
    }
}