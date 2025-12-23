package com.example.jetpackcomposeui.ui_activity.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeui.common_ui.ShimmerListItem
import com.example.jetpackcomposeui.navigation.FullScreen
import com.example.jetpackcomposeui.view_model.HomeViewModel
import com.example.jetpackcomposeui.view_model.UserListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(homeViewModel: HomeViewModel,viewModel: UserListViewModel = hiltViewModel()) {
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isShimmer by viewModel.isShimmer.collectAsState()
    val isErrorMessage by viewModel.isErrorMessage.collectAsState()
    val pullState = rememberPullToRefreshState()

    val listState = rememberSaveable(
        saver = LazyListState.Saver
    ) {
        LazyListState()
    }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
        state = pullState,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(state = listState) {

            if(isShimmer){
                items(20){
                    ShimmerListItem()
                }
            }else {
                items(viewModel.users) { user ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                homeViewModel.open(FullScreen.ChatUI(userData = user))
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = user.name, fontSize = 18.sp)
                            Text(text = user.email, fontSize = 12.sp)
                        }
                    }
                }
            }
            // pagination and bottom loader same as Inbox.
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