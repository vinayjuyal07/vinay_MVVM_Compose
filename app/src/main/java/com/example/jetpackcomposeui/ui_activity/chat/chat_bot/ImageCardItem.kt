package com.example.jetpackcomposeui.ui_activity.chat.chat_bot

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeui.R



@Composable
fun ImageCardItem(item: ChatItem.ImageCard) {
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
                    bottomEnd = 16.dp
                ),
                shadowElevation = 2.dp
            ) {
                Column (modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 5.dp
                )){

                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White // 👈 change color here
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text( modifier = Modifier
                        .fillMaxWidth() ,
                        text =  item.title,
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                }

            }

        }


    }
}
