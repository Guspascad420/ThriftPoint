package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.thriftpoint.R
import com.example.thriftpoint.data.remote_source.HttpEndpoint
import com.example.thriftpoint.models.Product
import com.example.thriftpoint.ui.theme.Gray80
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.viewmodels.ProductViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(viewModel: ProductViewModel, navController: NavHostController, productId: String) {
    val allProductsState = viewModel.allProductsState.collectAsState()
    var product: Product by remember { mutableStateOf(Product(0, "", "", 0)) }
    val formatter = DecimalFormat("#,###")

    LaunchedEffect(Unit) {
        allProductsState.value.data?.let { response ->
            product = response.data.filter { product ->
                product.id == productId.toInt()
            }[0]
        }
    }

    val sizes = listOf("S", "M", "L")
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { ProductDetailsTopBar(navController) },
        bottomBar = { ProductDetailsBottomBar(product, viewModel::addProductToCart, snackbarHostState) },
        snackbarHost = { CartSnackbar(snackbarHostState) }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                HttpEndpoint.IMG_BASE_URL + product.imageRes ,
                null,
                modifier = Modifier.padding(20.dp).size(400.dp)
            )
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    product.name, fontFamily = urbanist,
                    fontWeight = FontWeight.Medium, fontSize = 20.sp
                )
                Text(
                    "Rp ${formatter.format(product.price)}", Modifier.padding(top = 10.dp,
                        bottom = 20.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Row(Modifier.offset(y = (-8).dp), verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Ukuran", Modifier.padding(end = 25.dp), fontFamily = urbanist,
                            fontWeight = FontWeight.Medium, fontSize = 15.sp
                        )
                        sizes.forEach { size ->
                            val color =
                                if (viewModel.selectedSize == size) MaterialTheme.colorScheme.onSurface
                                else MaterialTheme.colorScheme.background
                            val textColor =
                                if (viewModel.selectedSize == size) MaterialTheme.colorScheme.background
                                else MaterialTheme.colorScheme.onSurface
                            Box {
                                Text(
                                    size,
                                    Modifier
                                        .drawBehind {
                                            if (viewModel.selectedSize != size) {
                                                drawCircle(
                                                    color = Color(0xFFDEDEDE),
                                                    radius = 90 / 1.8F
                                                )
                                            }
                                            drawCircle(
                                                color = color,
                                                radius = 90 / 2F
                                            )
                                        },
                                    fontFamily = urbanist, color = textColor
                                )
                            }
                            Spacer(Modifier.width(30.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetailsTopBar(navController: NavHostController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(vertical = 15.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically,
    ) {
        Surface(
            Modifier.size(50.dp),
            border = BorderStroke(1.dp, Color(0xFFE8ECF4)),
            shape = RoundedCornerShape(15.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBackIos, "Back Button", Modifier.padding(start = 8.dp))
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.IosShare, "Share")
        }
    }
}

@Composable
fun ProductDetailsBottomBar(product: Product, onBuyTapped: (Product) -> Unit,
    snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    Surface(shadowElevation = 10.dp, shape = RoundedCornerShape(25.dp, 25.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), Arrangement.Center
        ) {
            Surface(color = Gray80, shape = RoundedCornerShape(14.dp)) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(R.drawable.chat), "Chat",
                        Modifier.size(30.dp),
                        tint = Color(0xFFEBEBEB)
                    )
                }
            }
            Spacer(Modifier.width(10.dp))
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Tosca40)
            ) {
                Text(
                    "Beli", Modifier.padding(horizontal = 10.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.Medium,
                    fontSize = 20.sp, color = Tosca40
                )
            }
            Spacer(Modifier.width(10.dp))
            Button(
                onClick = {
                    onBuyTapped(product)
                    scope.launch {
                        snackbarHostState.showSnackbar("Berhasil Ditambahkan ke Keranjang")
                    }
                }, shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurface)
            ) {
                Text(
                    "+ Keranjang", Modifier.padding(16.dp, 2.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun CartSnackbar(snackbarHostState: SnackbarHostState) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            Modifier.padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
            containerColor = Color(0xFFD8F8D3), shape = RoundedCornerShape(20.dp)
        ) {
            Text(data.visuals.message, fontFamily = urbanist,
                fontWeight = FontWeight.Medium, color = Color(0xFF437538))
        }
    }
}