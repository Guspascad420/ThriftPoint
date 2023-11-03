package com.example.thriftpoint

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thriftpoint.ui.screens.AccountDetails
import com.example.thriftpoint.ui.screens.AddProduct
import com.example.thriftpoint.ui.screens.CartScreen
import com.example.thriftpoint.ui.screens.ConfirmOrder
import com.example.thriftpoint.ui.screens.EditProfile
import com.example.thriftpoint.ui.screens.ForgotPassword
import com.example.thriftpoint.ui.screens.HomeScreen
import com.example.thriftpoint.ui.screens.LoginScreen
import com.example.thriftpoint.ui.screens.NotificationScreen
import com.example.thriftpoint.ui.screens.ProductDetailsScreen
import com.example.thriftpoint.ui.screens.ProductsScreen
import com.example.thriftpoint.ui.screens.SignUpScreen
import com.example.thriftpoint.ui.screens.SplashScreen
import com.example.thriftpoint.ui.screens.WelcomePage
import com.example.thriftpoint.ui.screens.WishlistScreen
import com.example.thriftpoint.ui.theme.ThriftPointTheme
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.viewmodels.MainViewModel
import com.example.thriftpoint.viewmodels.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            val mainViewModel = hiltViewModel<MainViewModel>()
            val productViewModel = hiltViewModel<ProductViewModel>()

            ThriftPointTheme {
                // A surface container using the 'background' color from the theme
                NavHost(navController = navController, startDestination = NavRoute.SPLASH.name) {
                    composable(NavRoute.SPLASH.name) {
                        SplashScreen(mainViewModel, navController)
                    }
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
                        HomeScreen(mainViewModel, navController)
                    }
                    composable(NavRoute.NOTIFICATION.name) {
                        NotificationScreen(navController)
                    }
                    composable(NavRoute.ADD_PRODUCT.name) {
                        AddProduct(navController)
                    }
                    composable(NavRoute.PRODUCTS.name + "/{filter}") {
                        val filter = it.arguments?.getString("filter")
                        ProductsScreen(productViewModel, navController, filter)
                    }
                    composable(NavRoute.PRODUCT_DETAILS.name + "/{id}") {
                        val productId = it.arguments?.getString("id")
                        ProductDetailsScreen(productViewModel, navController, productId!!)
                    }
                    composable(NavRoute.PROFILE.name) {
                        AccountDetails(mainViewModel, navController)
                    }
                    composable(NavRoute.BAG.name) {
                        CartScreen(productViewModel, navController)
                    }
                    composable(NavRoute.EDIT_PROFILE.name) {
                        EditProfile(mainViewModel, navController)
                    }
                    composable(NavRoute.CONFIRM_ORDER.name + "/{totalPrice}") {
                        val totalPrice = it.arguments?.getString("totalPrice")
                        ConfirmOrder(mainViewModel, productViewModel, navController, totalPrice)
                    }
                    composable(NavRoute.WISHLIST.name) {
                        WishlistScreen(productViewModel, navController)
                    }
                }
            }
        }
    }
}
@HiltAndroidApp
class MainApplication : Application()
