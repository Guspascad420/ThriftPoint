package com.example.thriftpoint.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftpoint.data.datastore.DatastoreSource
import com.example.thriftpoint.data.remote_source.RemoteSource
import com.example.thriftpoint.data.remote_source.Resource
import com.example.thriftpoint.models.request.LoginRequest
import com.example.thriftpoint.models.request.SignUpRequest
import com.example.thriftpoint.models.response.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AuthUiState {
    object Success : AuthUiState
    object Error : AuthUiState
    object Default : AuthUiState
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val datastoreSource: DatastoreSource,
    private val remoteSource: RemoteSource
) : ViewModel() {
    var name by mutableStateOf(TextFieldValue(""))
    var email by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
    var passwordConfirm by mutableStateOf(TextFieldValue(""))
    var isPasswordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf(false)
    var authUiState: AuthUiState by mutableStateOf(AuthUiState.Default)


    val loginState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading())
    val signUpState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading())

    fun getToken() = datastoreSource.getToken()
    fun handleSignUp() {
        viewModelScope.launch {
            remoteSource.signUp(SignUpRequest(name.text, email.text, password.text)).collect {
                signUpState.value = it
            }
        }
    }

    fun handleLogin() {
        viewModelScope.launch {
            remoteSource.login(LoginRequest(email.text, password.text)).collect {
                loginState.value = it
            }
        }
    }

    suspend fun saveToken(token: String) = datastoreSource.setToken(token)
}