package com.rupp.news_app_demo_android.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.rupp.news_app_demo_android.ui.home.HomeScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val items = listOf(
        "Home" to Icons.Default.Home,
        "Explore" to Icons.Default.Explore,
        "Bookmark" to Icons.Default.Bookmark,
        "More" to Icons.Default.MoreHoriz
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(items[selectedItem].first) },
                actions = {
                    if (selectedItem == 0)
                        IconButton(onClick = {}) {
                            Icon(Icons.Outlined.Notifications, contentDescription = null)
                        } },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.second, contentDescription = item.first) },
                        label = { Text(item.first) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            when (selectedItem) {
                0 -> HomeScreenContent()
//                1 -> ExploreScreenContent()
//                2 -> BookMarkScreenContent()
//                3 -> MoreScreenContent()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview(){
    MainScreen()
}

