package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.data.remote_source.Resource
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.CommonTopBar
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.viewmodels.ProductViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(viewModel: ProductViewModel, navController: NavHostController) {
    val productsInWishlistState = viewModel.productsInWishlistState.collectAsState()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (viewModel.productsInWishlist.isEmpty()) {
            viewModel.getWishlist()
            delay(2000)
            productsInWishlistState.value.data?.let { response ->
                if (response.data != viewModel.productsInWishlist)
                    viewModel.productsInWishlist.addAll(response.data)
            }
        }
        isLoading = false
    }


    Scaffold(topBar = { CommonTopBar("Wishlist", navController) }) {
        Column(Modifier.padding(it)) {
            Spacer(Modifier.padding(10.dp))
            OutlinedTextField(
                value = "", onValueChange = { },
                Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF5F6FA),
                    unfocusedBorderColor = Color(0xFFF5F6FA)
                ),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    Icon(
                        painterResource(R.drawable.search), "Search",
                        Modifier
                            .size(25.dp)
                            .offset(x = 10.dp),
                        tint = Gray40
                    )
                },
                placeholder = {
                    Text(
                        "Telusuri...", Modifier.padding(start = 8.dp),
                        fontFamily = urbanist, color = Gray40
                    )
                }
            )
            Column(Modifier.padding(horizontal = 20.dp)) {
                if (!isLoading)
                    LazyVerticalGrid(GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        items(viewModel.productsInWishlist) { product ->
                            ProductCard(product, viewModel) {
                                navController.navigate(
                                    NavRoute.PRODUCT_DETAILS.name + "/" + product.id
                                )
                            }
                        }
                    }
                else Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}