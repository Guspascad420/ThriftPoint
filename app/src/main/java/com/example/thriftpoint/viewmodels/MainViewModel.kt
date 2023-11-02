package com.example.thriftpoint.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftpoint.data.datastore.DatastoreSource
import com.example.thriftpoint.data.remote_source.RemoteSource
import com.example.thriftpoint.data.remote_source.Resource
import com.example.thriftpoint.models.response.UserProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteSource: RemoteSource,
    private val datastoreSource: DatastoreSource
) : ViewModel() {
    var selectedItem by mutableStateOf("Home")
    var currentUserId by mutableStateOf("")

    var userDataState = MutableStateFlow<Resource<UserProfileResponse>>(Resource.Loading())

    fun getToken(onRetrieved:(String) -> Unit){
        viewModelScope.launch{
            datastoreSource.getToken().collectLatest{
                onRetrieved(it)
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            remoteSource.getUserData().collect {
                userDataState.value = it
            }
        }
    }
}