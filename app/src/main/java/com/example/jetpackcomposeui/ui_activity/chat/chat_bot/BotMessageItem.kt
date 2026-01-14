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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeui.R


@Composable
fun BotMessageItem(item: ChatItem.BotMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp,start = 5.dp,end = 70.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error_placeholder),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Surface(
                modifier = Modifier.padding(top=25.dp),
                color = Color(0xFFDCD5D5),
                shape = RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp // tail effect
                ),
                shadowElevation = 2.dp
            ) {
                Column (modifier = Modifier.padding(
                    10.dp
                )){
                    Text(
                        text = item.text,
                        color = Color.Black,
                        fontSize = 12.sp,
                        letterSpacing = 0.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text( modifier = Modifier
                        .fillMaxWidth() ,
                        text = item.time,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.End,
                    )
                }

            }

        }

    }
}
