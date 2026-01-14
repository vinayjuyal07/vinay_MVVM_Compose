package com.example.jetpackcomposeui.ui_activity.chat.chat_bot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeui.R


@Composable
fun UserMessageItem(item: ChatItem.UserMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 70.dp, end = 5.dp),
        horizontalArrangement = Arrangement.End
    ) {

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Surface(
                modifier = Modifier.padding(top=25.dp),
                color = Color(0xFFFF5A5A),
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 4.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ) {
                Column (modifier = Modifier.padding(
                    10.dp),
                    horizontalAlignment = Alignment.End) {
                    Text(
                        text = item.text,
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        maxLines = Int.MAX_VALUE,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Visible
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text( modifier = Modifier,
                        text = item.time,
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_user_placeholder),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(40.dp)
        )
    }
}
