package com.example.jetpackcomposeui.ui_activity.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeui.model.UserModel
import com.example.jetpackcomposeui.view_model.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    userData: UserModel,
    onBack: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    var text by rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()
    LaunchedEffect(userData.uid) {
        viewModel.startChat(userData.uid)
    }
    LaunchedEffect(viewModel.messages.size) {
        if (viewModel.messages.isNotEmpty()) {
            listState.animateScrollToItem(0)
        }
    }
    DisposableEffect(Unit) { onDispose {  viewModel.onClearMessageList() } }
    Scaffold(
        topBar = {
            ChatTopBar(
                title = "Chat ${userData.name}",
                onBack = onBack
            )
        },contentWindowInsets = WindowInsets(0,0,0,0)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true,
                contentPadding = PaddingValues(8.dp)
            )  {
                items(viewModel.messages.reversed(), key = { it.timestamp }) { msg ->
                    ChatBubble(
                        message = msg.message,
                        isMe = msg.senderId == userData.uid
                    )
                }
            }
            MessageInputBar(
                text = text,
                onTextChange = { text = it },
                onSendClick = {
                    if (text.isNotBlank()) {
                        viewModel.send(userData.uid, text)
                        text = ""
                    }
                }
            )
        }
    }
}
