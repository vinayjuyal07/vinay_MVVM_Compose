package com.example.jetpackcomposeui.network.repository_impl

import com.example.jetpackcomposeui.model.InboxRequest
import com.example.jetpackcomposeui.model.InboxResponse
import com.example.jetpackcomposeui.network.api.AuthApi
import com.example.jetpackcomposeui.network.api_client.ErrorParser
import com.example.jetpackcomposeui.network.base.ErrorEntity
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.model.LoginRequest
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.model.MessageType
import com.example.jetpackcomposeui.network.model.UserDetailsResponse
import com.example.jetpackcomposeui.network.repository.IAuthRepository
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

            delay(5000)
            if(loginRequest.email.equals("Vinay") && loginRequest.password.equals("123456")){
                val successResponse=LoginResponse("tieq7840r783nudyatigdsfbdyfuydfibf7","dkhfvdffvdfffl",87424344L)
                emit(ResultState.Success(successResponse))
            }else {
                val response = ErrorEntity.Error( 402,"Username and password does not match..!!")
                emit(ResultState.Error(response))
            }
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
                val successResponse = InboxResponse(getMessageDummyList(), request.page,
                    false, false, false)
                emit(ResultState.Success(successResponse))
            }else if(request.page<=10) {
                val successResponse = InboxResponse(getMessageDummyList(), request.page,
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

    var ids = 1100
    fun getMessageDummyList(): List<MessageType> {
        val imageList = arrayListOf<String>()
        imageList.add("https://api.slingacademy.com/public/sample-photos/27.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/28.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/11.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/6.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/7.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/8.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/9.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/10.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/12.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/13.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/39.jpeg")
        imageList.add("https://api.slingacademy.com/public/sample-photos/36.jpeg")
        val list = arrayListOf<MessageType>()
        repeat(10) { index ->
            ids++
            list.add(
                MessageType(
                    id = ids,
                    title = "Dummy Title $ids",
                    body = "This is a dummy message body for item $ids.",
                    image = imageList[index]
                )
            )
        }
        return list
    }

}