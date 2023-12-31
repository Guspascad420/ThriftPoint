package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.thriftpoint.data.remote_source.HttpEndpoint
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.viewmodels.MainViewModel
import com.example.thriftpoint.viewmodels.ProductViewModel
import kotlinx.coroutines.delay
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(productViewModel: ProductViewModel, navController: NavHostController) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    mainViewModel.selectedItem = "Bag"
    val productsInCartState = productViewModel.productsInCartState.collectAsState()
    val productsInWishlistState = productViewModel.productsInWishlistState.collectAsState()
    var count by remember { mutableIntStateOf(1) }
    var totalPrice by remember { mutableIntStateOf(0) }
    val formatter = DecimalFormat("#,###")
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (productViewModel.productsInWishlist.isEmpty() || productViewModel.productsInCart.isEmpty()) {
            productViewModel.getAllProductsInCart()
            productViewModel.getWishlist()
            delay(2000)
            productsInCartState.value.data?.let { response ->
                if (response.data != productViewModel.productsInCart)
                    productViewModel.productsInCart.addAll(response.data)
            }
            productsInWishlistState.value.data?.let { response ->
                if (response.data != productViewModel.productsInWishlist)
                    productViewModel.productsInWishlist.addAll(response.data)
            }
        }
        isLoading = false
    }

    Scaffold(
        topBar = { CartTopBar(navController) },
        bottomBar = { BottomBar(mainViewModel, navController) }
    ) {
        if (!isLoading)
        Column(Modifier.padding(it)) {
            Spacer(Modifier.height(25.dp))
            totalPrice = productViewModel.productsInCart.sumOf { product -> product.price }
            productViewModel.productsInCart.forEach { product ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(Modifier.padding(12.dp)) {
                        AsyncImage(
                            HttpEndpoint.IMG_BASE_URL + product.imageRes ,
                            null,
                            modifier = Modifier
                                .size(95.dp)
                                .clip(RoundedCornerShape(14.dp))
                        )
                        Column(Modifier.padding(start = 15.dp)) {
                            Text(
                                product.name, fontFamily = urbanist, fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text("Rp ${formatter.format(product.price)}",
                                fontFamily = urbanist, fontSize = 20.sp)
                            Spacer(Modifier.height(40.dp))
                            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Surface(
                                        Modifier.size(35.dp), shape = CircleShape,
                                        border = BorderStroke(1.dp, Color(0xFFDEDEDE)),
                                        color = MaterialTheme.colorScheme.background
                                    ) {
                                        IconButton(onClick = {
                                            if (count != 0) {
                                                count -= 1
                                            }
                                        }) {
                                            Icon(Icons.Default.KeyboardArrowDown, null)
                                        }
                                    }
                                    Text("$count", Modifier.padding(horizontal = 5.dp))
                                    Surface(
                                        Modifier.size(35.dp), shape = CircleShape,
                                        border = BorderStroke(1.dp, Color(0xFFDEDEDE)),
                                        color = MaterialTheme.colorScheme.background
                                    ) {
                                        IconButton(onClick = { count += 1 }) {
                                            Icon(Icons.Default.KeyboardArrowUp, null)
                                        }
                                    }
                                }
                                Row {
                                    Surface(
                                        Modifier.size(35.dp), shape = CircleShape,
                                        border = BorderStroke(1.dp, Color(0xFFDEDEDE)),
                                        color = MaterialTheme.colorScheme.background
                                    ) {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(Icons.Default.FavoriteBorder, null)
                                        }
                                    }
                                    Spacer(Modifier.width(5.dp))
                                    Surface(
                                        Modifier.size(35.dp), shape = CircleShape,
                                        border = BorderStroke(1.dp, Color(0xFFDEDEDE)),
                                        color = MaterialTheme.colorScheme.background
                                    ) {
                                        IconButton(onClick = {
                                            productViewModel.removeProductFromCart(product)
                                        }) {
                                            Icon(Icons.Default.Delete, null)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                Modifier.fillMaxWidth(),
                Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Wujudkan Wishlistmu!", Modifier.padding(start = 20.dp),
                    fontFamily = urbanist, fontSize = 21.sp
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        "Lihat Semua", fontFamily = urbanist,
                        fontSize = 16.sp, color = Tosca40
                    )
                }
            }
            LazyRow {
                items(productViewModel.productsInWishlist) { product ->
                    Card(
                        Modifier.padding(start = 20.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Row(Modifier.padding(12.dp)) {
                            AsyncImage(
                                HttpEndpoint.IMG_BASE_URL + product.imageRes ,
                                null,
                                modifier = Modifier
                                    .size(95.dp)
                                    .clip(RoundedCornerShape(14.dp))
                            )
                            Column(Modifier.padding(start = 15.dp)) {
                                Text(
                                    product.name, fontFamily = urbanist, fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text("Rp ${formatter.format(product.price)}",
                                    fontFamily = urbanist, fontSize = 20.sp)
                                Spacer(Modifier.height(40.dp))
                                Row {
                                    OutlinedButton(
                                        onClick = { /*TODO*/ },
                                        border = BorderStroke(2.dp, Tosca40)
                                    ) {
                                        Text("+ Keranjang", Modifier.padding(10.dp, 4.dp))
                                    }
                                    Spacer(Modifier.width(10.dp))
                                    Surface(
                                        Modifier.size(35.dp), shape = CircleShape,
                                        border = BorderStroke(1.dp, Color(0xFFDEDEDE)),
                                        color = MaterialTheme.colorScheme.background
                                    ) {
                                        IconButton(onClick = {
                                           productViewModel.removeProductFromCart(product)
                                        }) {
                                            Icon(Icons.Default.Delete, null)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Box(Modifier.fillMaxSize(), Alignment.BottomStart) {
                Surface(
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp, 25.dp),
                    shadowElevation = 90.dp
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(25.dp), Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "Total Harga",
                                fontFamily = urbanist,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "Rp ${formatter.format(totalPrice)}", fontFamily = urbanist,
                                fontWeight = FontWeight.Bold, color = Tosca40
                            )
                        }
                        Button(
                            onClick = { navController.navigate(NavRoute.CONFIRM_ORDER.name + "/$totalPrice") },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Text(
                                "Beli Sekarang", Modifier.padding(5.dp, 3.dp),
                                fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
        else Box(Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun CartTopBar(navController: NavHostController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp),
        Arrangement.SpaceBetween, Alignment.CenterVertically
    ) {
        Surface(
            Modifier.size(50.dp),
            border = BorderStroke(1.dp, Color(0xFFE8ECF4)),
            shape = RoundedCornerShape(15.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, "Back Button")
            }
        }
        Box(Modifier.fillMaxWidth(), Alignment.Center) {
            Text(
                "Keranjang Saya", fontFamily = urbanist,
                fontWeight = FontWeight.SemiBold, fontSize = 23.sp
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Favorite, "Wishlist")
        }
    }
}