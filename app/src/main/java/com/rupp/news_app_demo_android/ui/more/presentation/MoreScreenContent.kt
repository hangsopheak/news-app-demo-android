package com.rupp.news_app_demo_android.ui.more.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MoreScreenContent(
    onOfflineReadingClick: () -> Unit = {},
    onReadArticlesClick: () -> Unit = {},
    onNotificationsClick: () -> Unit= {},
    onAppearanceClick: () -> Unit= {},
    onAboutUsClick: () -> Unit= {},
    onPrivacyClick: () -> Unit= {},
    onTermsClick: () -> Unit= {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Section: Reading Preferences
        item {
            PreferenceSectionTitle("Reading Preferences")
            PreferenceItem("Offline Reading", onClick = onOfflineReadingClick)
            PreferenceItem("Read Articles", onClick = onReadArticlesClick)
        }

        // Section: App Settings
        item {
            PreferenceSectionTitle("App Settings")
            PreferenceItem("Notifications", onClick = onNotificationsClick)
            PreferenceItem("Appearance", onClick = onAppearanceClick)
        }

        // Section: About
        item {
            PreferenceSectionTitle("About")
            PreferenceItem("About Us", onClick = onAboutUsClick)
            PreferenceItem("Privacy Policy", onClick = onPrivacyClick)
            PreferenceItem("Terms & Conditions", onClick = onTermsClick)
            AppVersionItem("Version 1.0.0")
        }
    }
}

@Composable
fun PreferenceSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun PreferenceItem(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
fun AppVersionItem(version: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = version,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}