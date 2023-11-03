package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.thriftpoint.R
import com.example.thriftpoint.data.remote_source.HttpEndpoint
import com.example.thriftpoint.models.Product
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.viewmodels.ProductViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(viewModel: ProductViewModel, navController: NavHostController, filterName: String?) {
    val allProductsState = viewModel.allProductsState.collectAsState()
    val productsInWishlistState = viewModel.productsInWishlistState.collectAsState()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.getAllProducts()
        viewModel.getWishlist()
        delay(2000)
        productsInWishlistState.value.data?.let { response ->
            viewModel.productsInWishlist.addAll(response.data)
        }
        isLoading = false
    }

    Scaffold(
        topBar = { HomeTopBar(navController) },
    ) {
        Column(Modifier.padding(it)) {
            LazyRow {
                items(filters) { filter ->
                    val paddingEnd = if (filter.name == "Tas") 20.dp else 0.dp
                    val imgPadding = if (filter.name == "Semua") 15.dp else 0.dp
                    Column(
                        Modifier.padding(start = 20.dp, end = paddingEnd),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            Modifier.size(75.dp),
                            shape = CircleShape,
                            color = if (filterName.toString() == filter.name) Color.Black
                            else Color(0xFFEAECE1)
                        ) {
                            Image(
                                painterResource(filter.image), null,
                                Modifier.padding(imgPadding)
                            )
                        }
                        Text(
                            filter.name, fontFamily = urbanist,
                            fontSize = 17.sp, color = Color(0xFF545F71)
                        )
                    }
                }
            }
            Spacer(Modifier.padding(top = 10.dp))
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    "Produk Terbaru", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
                Spacer(Modifier.height(15.dp))
                if (!isLoading)
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        allProductsState.value.data?.let { response ->
                            items(response.data) { product ->
                                ProductCard(product, viewModel) {
                                    navController.navigate(
                                        NavRoute.PRODUCT_DETAILS.name + "/" + product.id
                                    )
                                }
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

@Composable
fun ProductCard(product: Product, viewModel: ProductViewModel, navigateToDetails: () -> Unit) {
    var wishlistIcon by remember {
        mutableStateOf(if (product in viewModel.productsInWishlist)
            Icons.Default.Favorite else Icons.Default.FavoriteBorder)
    }

    var wishlistIconTint by remember {
        mutableStateOf(if (product in viewModel.productsInWishlist)
            Color.Red else Color(0xFF9BA5B7))
    }

    Column(Modifier.padding(top = 10.dp).clickable { navigateToDetails() }) {
        Box(Modifier.wrapContentSize()) {
            AsyncImage(
                HttpEndpoint.IMG_BASE_URL + product.imageRes ,
                null,
                modifier = Modifier
                    .width(180.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Box(Modifier.fillMaxWidth(), Alignment.TopEnd) {
                IconButton(onClick = {
                    if (wishlistIconTint != Color.Red) {
                        viewModel.addProductToWishlist(product)
                        wishlistIcon = Icons.Default.Favorite
                        wishlistIconTint = Color.Red
                    } else {
                        viewModel.removeProductFromWishlist(product)
                        wishlistIcon = Icons.Default.FavoriteBorder
                        wishlistIconTint = Color(0xFF9BA5B7)
                    }
                    println(viewModel.productsInWishlist)
                }) {
                    Icon(wishlistIcon, null, tint = wishlistIconTint)
                }
            }
        }
        Text(
            product.name, fontFamily = urbanist,
            fontWeight = FontWeight.Medium, fontSize = 14.sp,
            lineHeight = 18.sp
        )
        Text(
            "${product.price}",
            fontFamily = urbanist,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(R.drawable.frame_19129), null,
                Modifier.padding(top = 4.dp, end = 5.dp))
            Text("prelovedthings", fontFamily = urbanist,
                fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

    }
}