package com.example.jetpackcomposeui.ui_activity.inbox

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeui.common_ui.BottomLoader
import com.example.jetpackcomposeui.common_ui.ShimmerListItem
import com.example.jetpackcomposeui.view_model.HomeViewModel
import com.example.jetpackcomposeui.view_model.InboxViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(homeViewModel: HomeViewModel,viewModel: InboxViewModel  = hiltViewModel()) {
    val items by viewModel.items.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    val isShimmer by viewModel.isShimmer.collectAsState()

    val isErrorMessage by viewModel.isErrorMessage.collectAsState()

    val pullState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
        state = pullState,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            if(isShimmer){
                items(20){
                    ShimmerListItem()
                }
            }else {
                items(
                    items,
                    key = { it }
                ) { item ->
                    SwipeableMessageItem(
                        mMessageType = item,
                        onEdit = { viewModel.edit(item) },
                        onDelete = { viewModel.delete(item) }
                    )
                }
            }
            // ⭐ PAGINATION TRIGGER
            item {
                LaunchedEffect(items.size) {
                    if (items.isNotEmpty() && !isRefreshing && !isLoadingMore) {
                        viewModel.loadNextPage()
                    }
                }
            }
            // ⭐ BOTTOM LOADER
            if (isLoadingMore) {
                item {
                    BottomLoader()
                }
            }
        }
    }
    // ---------------------------------------------------------
    // 🔥 Global reusable top snackbar
    // ---------------------------------------------------------
    if(!isErrorMessage.isNullOrEmpty()){
        homeViewModel.showSnackbar(isErrorMessage)
        viewModel.clearErrorMessage()
    }

}