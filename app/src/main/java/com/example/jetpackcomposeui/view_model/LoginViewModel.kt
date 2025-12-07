package com.example.jetpackcomposeui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.network.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: AuthUseCase): ViewModel() {
    private val _uiState = MutableLiveData<ResultState<LoginResponse>>()
    val uiState: LiveData<ResultState<LoginResponse>> = _uiState
    fun loginApi(userName:String,password: String){
        viewModelScope.launch (Dispatchers.IO){
            loginUseCase.hitLoginApi(userName,password).collect { result ->
                Log.d("TAG", "loginApi: "+result)
                _uiState.postValue(result)   // 🔥 sends Loading / Success / Error to UI
            }
        }
    }
}
