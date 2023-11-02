package com.example.thriftpoint.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.thriftpoint.data.remote_source.Resource
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.viewmodels.MainViewModel

@Composable
fun SplashScreen(viewModel: MainViewModel, navController: NavController) {
    val userDataState = viewModel.userDataState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.getToken { token ->
            if(token.isEmpty()){
                navController.navigate(NavRoute.WELCOME.name) {
                    popUpTo(NavRoute.SPLASH.name) {
                        inclusive = true
                    }
                }
            } else {
                viewModel.getUserData()
            }
        }
    }

    LaunchedEffect(key1 = userDataState.value) {
        when(userDataState.value){
            is Resource.Error -> { }
            is Resource.Loading -> { }
            is Resource.Success -> {
                navController.navigate(NavRoute.HOME.name)
            }
        }
    }
}