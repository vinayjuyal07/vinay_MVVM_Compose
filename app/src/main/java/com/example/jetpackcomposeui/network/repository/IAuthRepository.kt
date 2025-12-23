package com.example.jetpackcomposeui.network.repository

import com.example.jetpackcomposeui.model.InboxRequest
import com.example.jetpackcomposeui.model.InboxResponse
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.model.LoginRequest
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.model.UserModel
import com.example.jetpackcomposeui.network.model.UserDetailsResponse
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun hitLoginApiCall(loginRequest: LoginRequest): Flow<ResultState<LoginResponse>>

    suspend fun hitUserDetailsApiCall(userId: Int): Flow<ResultState<UserDetailsResponse>>

    suspend fun hitInboxListApi(request : InboxRequest): Flow<ResultState<InboxResponse>>

    suspend fun hitChatListApi(): Flow<ResultState<List<UserModel>>>
}