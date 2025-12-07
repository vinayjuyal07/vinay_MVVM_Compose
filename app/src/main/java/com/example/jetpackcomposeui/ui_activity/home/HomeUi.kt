package com.example.jetpackcomposeui.ui_activity.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.R


@Preview(showSystemUi = true)
@Composable
fun HomeUi () {
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentScale = ContentScale.FillHeight
        )
        /*Text(text = "Access Token: ${loginResponse?.accessToken}")
        Text(text = "Refresh Token: ${loginResponse?.refreshToken}")
        Text(text = "Expires In: ${loginResponse?.expiresIn}")*/
    }
}