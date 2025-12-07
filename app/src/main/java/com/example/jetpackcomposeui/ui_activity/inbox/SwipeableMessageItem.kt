package com.example.jetpackcomposeui.ui_activity.inbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcomposeui.R
import com.example.jetpackcomposeui.common_ui.HorizontalDividerLine
import com.example.jetpackcomposeui.model.MessageType
import com.kevinnzou.compose.swipebox.SwipeBox
import com.kevinnzou.compose.swipebox.SwipeDirection
import com.kevinnzou.compose.swipebox.widget.SwipeIcon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableMessageItem(
    mMessageType : MessageType,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        SwipeBox(
            modifier = Modifier.fillMaxWidth(),
            swipeDirection = SwipeDirection.Both,
            startContentWidth = 80.dp,
            startContent = { swipeableState, endSwipeProgress ->
                SwipeIcon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    background = Color(0xFFFA1E32),
                    weight = 1f,
                    iconSize = 20.dp
                ) {
                    coroutineScope.launch {
                        swipeableState.animateTo(0)
                    }
                }
            },
            endContentWidth = 140.dp,
            endContent = { swipeableState, endSwipeProgress ->
                SwipeIcon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    tint = Color.Blue,
                    background = Color(0xDCE5D8D9),
                    weight = 1f,
                    iconSize = 20.dp
                ) {
                    coroutineScope.launch {
                        swipeableState.animateTo(0)
                    }
                }
                SwipeIcon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More",
                    tint = Color.Blue,
                    background = Color(0xDCE5D8D9),
                    weight = 1f,
                    iconSize = 20.dp
                ) {
                    coroutineScope.launch {
                        swipeableState.animateTo(0)
                    }
                }

            }
        ) { _, _, _ ->
             MessageCard(mMessageType)
        }
        HorizontalDividerLine()
    }
}

// @Preview(showSystemUi = true)
@Composable
fun MessageCard(mMessageType : MessageType) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = colorResource(id = R.color.white)),
        contentAlignment = Alignment.Center
    ) {

        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = mMessageType.image,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_user_placeholder),
                error = painterResource(R.drawable.ic_error_placeholder)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f) // takes remaining width
            ) {
                Text(
                    text =  "Title : ${mMessageType.title}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Subject : ${mMessageType.body}",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 0.dp)
                )
                Text(
                    text = "Email - vinayjuyal07@gmail.com",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 0.dp)
                )
            }

        }
    }
}
