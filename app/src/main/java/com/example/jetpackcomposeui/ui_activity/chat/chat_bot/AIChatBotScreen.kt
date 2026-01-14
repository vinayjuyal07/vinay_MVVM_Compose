package com.example.jetpackcomposeui.ui_activity.chat.chat_bot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIChatBotScreen(
    userId: String,
    onBack: () -> Unit,
    viewModel: AIChatBotViewModel = hiltViewModel()
) {
    var text by rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(viewModel.chatList.size) {
        if (viewModel.chatList.isNotEmpty()) {
            listState.animateScrollToItem(viewModel.chatList.size)
        }
    }
    DisposableEffect(Unit) { onDispose {  viewModel.onClearMessageList() } }
    Scaffold(
        topBar = {
            AiChatBotTopBar(
                title = "Ai Assistant",
                onBack = onBack
            )
        },contentWindowInsets = WindowInsets(0,0,0,0)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = false,
                contentPadding = PaddingValues(8.dp)
            )  {
                items(viewModel.chatList,key = { it.hashCode() }) { item ->
                    when (item) {
                        is ChatItem.BotMessage -> BotMessageItem(item)
                        is ChatItem.UserMessage -> UserMessageItem(item)
                        is ChatItem.ImageCard -> ImageCardItem(item)
                    }
                }
            }
            ChatBotMessageInputBar(
                text = text,
                onTextChange = { text = it },
                onSendClick = {
                    if (text.isNotBlank()) {
                        viewModel.send(text)
                        text = ""
                    }
                }
            )
        }
    }
}
