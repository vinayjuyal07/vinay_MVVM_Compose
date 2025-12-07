package com.example.jetpackcomposeui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeui.model.InboxRequest
import com.example.jetpackcomposeui.model.MessageType
import com.example.jetpackcomposeui.network.base.ResultState
import com.example.jetpackcomposeui.network.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InboxViewModel @Inject constructor(private val loginUseCase: AuthUseCase): ViewModel() {

    private val _items = MutableStateFlow<List<MessageType>>(emptyList())
    val items = _items.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()
    private val _isShimmer = MutableStateFlow(false)
    val isShimmer = _isShimmer.asStateFlow()
    private val _isErrorMessage = MutableStateFlow("")
    val isErrorMessage = _isErrorMessage.asStateFlow()
    private var currentPage = 0
    private var hasMorePages = true

    init {
        loadFirstPage()
    }
    fun loadFirstPage() {
        loadInboxListApi(page = 0, isFirst = true)
    }
    fun refresh() {
        loadInboxListApi(page = 0, isRefresh = true)
    }
    fun loadNextPage() {
        if (_isLoadingMore.value || !hasMorePages) return
        loadInboxListApi(page = currentPage, isLoadMore = true)
    }

    private fun loadInboxListApi(page: Int,
                           isFirst: Boolean = false,
                           isRefresh: Boolean = false,
                           isLoadMore: Boolean = false){
        viewModelScope.launch (Dispatchers.IO){
            loginUseCase.hitInboxListApi(InboxRequest(page = page)).collect {
                when(it){
                    is ResultState.Loading -> {
                        when{
                            isFirst -> _isShimmer.value = true
                            isRefresh -> _isRefreshing.value = true
                            isLoadMore -> _isLoadingMore.value=true
                            else ->{}
                        }
                    }
                    is ResultState.Success -> {
                        _items.value = if(isFirst || isRefresh)  it.data.items else _items.value + it.data.items
                        currentPage = page+1
                        hasMorePages = it.data.hasMore
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

    fun clearErrorMessage(){
        _isErrorMessage.value=""
    }
    private fun onClearMutableState(){
        _isShimmer.value = false
        _isLoadingMore.value=false
        _isRefreshing.value = false
    }

    fun delete(item: MessageType) {
        _items.value = _items.value - item
    }

    fun edit(item: MessageType) {
        // Handle edit flow
    }
}