package com.example.jetpackcomposeui.ui_activity.chat.chat_bot
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.R


@Composable
fun ChatBotMessageInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                colorResource(R.color.light_gray),
                shape = RoundedCornerShape(0.dp))
            .imePadding()
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(modifier = Modifier.weight(1f)
            .border(
                width = 1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f).background(Color.Transparent),
                placeholder = { Text("Tell me what do you want?") },
                maxLines = 4,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.width(3.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_mic_none_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        FloatingActionButton(
            onClick = onSendClick,
            containerColor = Color.Red,
            contentColor = Color.White,
            modifier = Modifier.size(48.dp),
            shape = CircleShape
        ) {
            Icon(Icons.Default.Send, contentDescription = null, tint = Color.White)
        }
    }
}