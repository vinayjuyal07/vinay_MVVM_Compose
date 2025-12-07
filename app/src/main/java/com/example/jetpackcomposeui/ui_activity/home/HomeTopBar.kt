package com.example.jetpackcomposeui.ui_activity.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.R
import com.example.jetpackcomposeui.navigation.Routs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.red),        // Topbar background color
            scrolledContainerColor = colorResource(id = R.color.red),   // FIX!
            titleContentColor = colorResource(id = R.color.white),           // Title text color
            navigationIconContentColor = colorResource(id = R.color.white),  // Icon color
            actionIconContentColor =colorResource(id = R.color.white)       // Menu icons color
        ),
        title = {
            Text(
                text = title,
                maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }
        },
        scrollBehavior = scrollBehavior
    )
}

