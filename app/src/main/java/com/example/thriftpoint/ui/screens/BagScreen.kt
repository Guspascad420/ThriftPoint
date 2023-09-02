package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BagScreen(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    viewModel.selectedItem = "Bag"
    var count by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = { BagTopBar(navController) },
        bottomBar = { BottomBar(viewModel, navController) }
    ) {
        Column(Modifier.padding(it)) {
            if (productsInBag.isNotEmpty()) {
                productsInBag.forEach { product ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(14.dp),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(7.dp)) {
                            Image(painterResource(product.img), null,
                                Modifier
                                    .width(95.dp)
                                    .clip(RoundedCornerShape(14.dp)))
                            Column {
                                Text(product.name, fontFamily = urbanist, fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium)
                                Text(product.price, fontFamily = urbanist, fontSize = 20.sp)
                                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                                    Row {
                                        Surface(shape = CircleShape) {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(Icons.Default.KeyboardArrowDown, null)
                                            }
                                        }
                                        Text("$count")
                                        Surface(shape = CircleShape) {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(Icons.Default.KeyboardArrowUp, null)
                                            }
                                        }
                                    }
                                    Row {
                                        Surface(shape = CircleShape) {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(Icons.Default.FavoriteBorder, null)
                                            }
                                        }
                                        Surface(shape = CircleShape) {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(Icons.Default.Delete, null)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BagTopBar(navController: NavHostController) {
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