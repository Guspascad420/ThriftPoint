package com.example.thriftpoint.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

sealed interface AuthUiState {
    object Success : AuthUiState
    object Error : AuthUiState
    object Default : AuthUiState
}

class AuthViewModel : ViewModel() {
    var name by mutableStateOf(TextFieldValue(""))
    var email by mutableStateOf(TextFieldValue(""))
    var password by mutableStateOf(TextFieldValue(""))
    var passwordConfirm by mutableStateOf(TextFieldValue(""))
    var isPasswordVisible by mutableStateOf(false)
    var error by mutableStateOf(false)
    var authUiState: AuthUiState by mutableStateOf(AuthUiState.Default)

    var auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    fun handleSignUp() {
        auth.createUserWithEmailAndPassword(email.text, password.text)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = hashMapOf(
                        "name" to name.text,
                        "email" to email.text,
                        "phone_number" to ""
                    )
                    auth.currentUser?.let { db.collection("users").document(it.uid).set(user) }
                    authUiState = AuthUiState.Success
                }
            }
    }

    fun handleLogin() {
        auth.signInWithEmailAndPassword(email.text, password.text)
            .addOnCompleteListener { task ->
                authUiState = if (task.isSuccessful) AuthUiState.Success else AuthUiState.Error
            }
    }
}