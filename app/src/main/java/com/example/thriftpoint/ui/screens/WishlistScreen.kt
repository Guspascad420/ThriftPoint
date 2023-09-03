package com.example.thriftpoint.ui.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.CommonTopBar
import com.example.thriftpoint.utils.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavHostController) {
    Scaffold(topBar = { CommonTopBar("Wishlist", navController) }) {
        LazyVerticalGrid(GridCells.Fixed(2), Modifier.padding(it)) {
            item(span = { GridItemSpan(3) }) {
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
            }
            items(productsInWishlist) { product ->
                ProductCard(product) {
                    navController.navigate(
                        NavRoute.PRODUCT_DETAILS.name + "/" + product.name
                    )
                }
            }
        }
    }
}