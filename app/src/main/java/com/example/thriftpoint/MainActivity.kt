package com.example.thriftpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thriftpoint.ui.screens.AccountDetails
import com.example.thriftpoint.ui.screens.AddProduct
import com.example.thriftpoint.ui.screens.ForgotPassword
import com.example.thriftpoint.ui.screens.HomeScreen
import com.example.thriftpoint.ui.screens.LoginScreen
import com.example.thriftpoint.ui.screens.NotificationScreen
import com.example.thriftpoint.ui.screens.ProductDetailsScreen
import com.example.thriftpoint.ui.screens.ProductsScreen
import com.example.thriftpoint.ui.screens.SignUpScreen
import com.example.thriftpoint.ui.screens.WelcomePage
import com.example.thriftpoint.ui.theme.ThriftPointTheme
import com.example.thriftpoint.utils.NavRoute

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            ThriftPointTheme {
                // A surface container using the 'background' color from the theme
                NavHost(navController = navController, startDestination = NavRoute.WELCOME.name) {
                    composable(NavRoute.WELCOME.name) {
                        WelcomePage(navController)
                    }
                    composable(NavRoute.LOGIN.name) {
                        LoginScreen(navController)
                    }
                    composable(NavRoute.SIGNUP.name) {
                        SignUpScreen(navController)
                    }
                    composable(NavRoute.FORGOT_PASSWORD.name) {
                        ForgotPassword(navController)
                    }
                    composable(NavRoute.HOME.name) {
                        HomeScreen(navController)
                    }
                    composable(NavRoute.NOTIFICATION.name) {
                        NotificationScreen(navController)
                    }
                    composable(NavRoute.ADD_PRODUCT.name) {
                        AddProduct(navController)
                    }
                    composable(NavRoute.PRODUCTS.name + "/{filter}") {
                        val filter = it.arguments?.getString("filter")
                        ProductsScreen(navController, filter)
                    }
                    composable(NavRoute.PRODUCT_DETAILS.name + "/{name}") {
                        val productName = it.arguments?.getString("name")
                        ProductDetailsScreen(navController, productName)
                    }
                    composable(NavRoute.PROFILE.name) {
                        AccountDetails(navController)
                    }
                }
            }
        }
    }
}