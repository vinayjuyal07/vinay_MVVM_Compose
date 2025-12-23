package com.example.jetpackcomposeui.network.use_case

import com.example.jetpackcomposeui.model.InboxRequest
import com.example.jetpackcomposeui.model.InboxResponse
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.model.LoginRequest
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.model.UserModel
import com.example.jetpackcomposeui.network.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthUseCase @Inject constructor(private val authRepository: IAuthRepository) {

    suspend fun hitLoginApi(email: String="Vinay", password: String="12345"): Flow<ResultState<LoginResponse>> {
        return authRepository.hitLoginApiCall(LoginRequest(email,password))
    }

    suspend fun hitInboxListApi(request : InboxRequest): Flow<ResultState<InboxResponse>> {
        return authRepository.hitInboxListApi(request)
    }

    suspend fun hitChatListApi(): Flow<ResultState<List<UserModel>>> {
        return authRepository.hitChatListApi()
    }

}