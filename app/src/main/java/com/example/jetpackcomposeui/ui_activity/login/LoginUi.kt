package com.example.jetpackcomposeui.ui_activity.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.ActionButtonConstant
import com.example.jetpackcomposeui.R

@Composable
fun LoginUi(
    email: String,
    password: String,
    showPassword: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick:()-> Unit,
    onGoogleSignInClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(100.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.FillHeight
            )

            Spacer(Modifier.height(20.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = { Text(ActionButtonConstant.EMAIL) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = { Text(ActionButtonConstant.PASSWORD) },
                visualTransformation = if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (showPassword) R.drawable.ic_eye_open
                    else R.drawable.ic_eye_closed

                    IconButton(onClick = onTogglePassword) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                },
                singleLine = true
            )

            Spacer(Modifier.height(10.dp))

            // Forgot Password
            Text(
                text = ActionButtonConstant.FORGOT_PASSWORD,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .clickable {
                        onForgotPasswordClick()
                    },
                textAlign = TextAlign.End,
                color = Color(0xFF0066CC),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.red
                    )
                )
            ) {
                Text(ActionButtonConstant.SIGN_IN, Modifier.padding(8.dp))
            }

            Spacer(Modifier.height(20.dp))

            // create row for divider and text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.gray),
                    modifier = Modifier.weight(1f)
                )
                Text(text = ActionButtonConstant.OR, color = colorResource(id = R.color.gray))
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.gray),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(12.dp))

            // Google Login
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.light_gray
                    )
                ),
                onClick = onGoogleSignInClick) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = ""
                    )
                    Text(
                        text = ActionButtonConstant.SIGN_IN_WITH_GOOGLE,
                        modifier = Modifier.padding(8.dp),
                        color = colorResource(id = R.color.app_color)
                    )
                }
            }
        }
    }
}
