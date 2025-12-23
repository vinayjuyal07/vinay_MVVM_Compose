package com.example.jetpackcomposeui.network.repository_impl

import com.example.jetpackcomposeui.model.InboxRequest
import com.example.jetpackcomposeui.model.InboxResponse
import com.example.jetpackcomposeui.model.LoginRequest
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.model.UserModel
import com.example.jetpackcomposeui.network.api.AuthApi
import com.example.jetpackcomposeui.network.api_client.ErrorParser
import com.example.jetpackcomposeui.network.base.ErrorEntity
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.network.model.UserDetailsResponse
import com.example.jetpackcomposeui.network.repository.IAuthRepository
import com.example.jetpackcomposeui.utility.CommonUtils
import com.example.jetpackcomposeui.utility.FirebaseUtil
import com.example.jetpackcomposeui.utility.FirebaseUtil.observeUsersList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi): ErrorParser(), IAuthRepository {
    override suspend fun hitLoginApiCall(loginRequest: LoginRequest): Flow<ResultState<LoginResponse>> {
        return flow {
            emit(ResultState.Loading())
            //val response = authApi.login(loginRequest)
            //emit(ResultState.Success(response))

            // local handling
            /*delay(5000)
            if(loginRequest.email.equals("Vinay") && loginRequest.password.equals("123456")){
                val successResponse=LoginResponse("tieq7840r783nudyatigdsfbdyfuydfibf7","dkhfvdffvdfffl",87424344L)
                emit(ResultState.Success(successResponse))
            }else {
                val response = ErrorEntity.Error( 402,"Username and password does not match..!!")
                emit(ResultState.Error(response))
            }*/
            emit(FirebaseUtil.loginFirebaseAuth(loginRequest.email, loginRequest.password))
        }.catch {
            emit(ResultState.Error(errorHandle(it).error))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun hitUserDetailsApiCall(userId: Int): Flow<ResultState<UserDetailsResponse>> {
        return flow {
            emit(ResultState.Loading())
            val response = authApi.getUserDetailsApi(userId)
            emit(ResultState.Success(response))
        }.catch {
            emit(ResultState.Error(errorHandle(it).error))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun hitInboxListApi(request : InboxRequest): Flow<ResultState<InboxResponse>> {
        return flow {
            emit(ResultState.Loading())
             // val response = authApi.getUserDetailsApi(userId)
            //emit(ResultState.Success(response))
            delay(5000)
            if(request.page==10) {
                val successResponse = InboxResponse(
                    CommonUtils.getMessageDummyList(), request.page,
                    false, false, false)
                emit(ResultState.Success(successResponse))
            }else if(request.page<=10) {
                val successResponse = InboxResponse(CommonUtils.getMessageDummyList(), request.page,
                    request.hasMore, false, false)
                emit(ResultState.Success(successResponse))
            }else {
                val response = ErrorEntity.Error(601, "No more data here.")
                emit(ResultState.Error(response))
            }
        }.catch {
            emit(ResultState.Error(errorHandle(it).error))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun hitChatListApi(): Flow<ResultState<List<UserModel>>> {
        return flow {
            emit(ResultState.Loading())
            observeUsersList().collect { emit(it) }
        }.catch {
            emit(ResultState.Error(errorHandle(it).error))
        }.flowOn(Dispatchers.IO)
    }

}