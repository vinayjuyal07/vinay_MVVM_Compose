package com.example.jetpackcomposeui.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeui.model.UserModel
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.network.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val loginUseCase: AuthUseCase): ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val _isShimmer = MutableStateFlow(false)
    val isShimmer = _isShimmer.asStateFlow()
    var users by mutableStateOf<List<UserModel>>(emptyList())
        private set
    private val _isErrorMessage = MutableStateFlow("")
    val isErrorMessage = _isErrorMessage.asStateFlow()

    init {
        loadUserListListening(isFirst = true)
    }
    fun loadUserListListening(isFirst: Boolean = false,
                       isRefresh: Boolean = false) {
        viewModelScope.launch (Dispatchers.IO){
            loginUseCase.hitChatListApi().collect {
                when(it){
                    is ResultState.Loading -> {
                        when{
                            isFirst -> _isShimmer.value = true
                            isRefresh -> _isRefreshing.value = true
                            else ->{}
                        }
                    }
                    is ResultState.Success -> {
                        users = it.data
                        onClearMutableState()
                    }
                    is ResultState.Error-> {
                        onClearMutableState()
                        _isErrorMessage.value=it.error.errorMessage ?: "Something went wrong"
                    }
                }
            }
        }
    }
    fun refresh() {
        loadUserListListening(isRefresh = true)
    }
    private fun onClearMutableState(){
        _isShimmer.value = false
        _isRefreshing.value = false
    }
    fun clearErrorMessage(){
        _isErrorMessage.value=""
    }

}



