package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.models.Filter
import com.example.thriftpoint.models.User
import com.example.thriftpoint.ui.theme.Dark80
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.viewmodels.MainViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

val filters = listOf(
    Filter("Semua", R.drawable.group_19115),
    Filter("Wanita", R.drawable.rectangle_344),
    Filter("Pria", R.drawable.hoodie),
    Filter("Anak", R.drawable.baju_anak),
    Filter("Topi", R.drawable.topi),
    Filter("Sepatu", R.drawable.sepatu),
    Filter("Tas", R.drawable.tas),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {
    val userData = viewModel.user

    LaunchedEffect(key1 = true) {
        viewModel.currentUserId = Firebase.auth.currentUser?.uid.toString()
        if (userData == User()) {
            viewModel.getUserData(viewModel.currentUserId)
        }
    }
    Scaffold(
        topBar = { HomeTopBar(navController) },
        bottomBar = { BottomBar(viewModel, navController) }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    "Tren Minggu Ini", fontSize = 25.sp,
                    fontFamily = urbanist, fontWeight = FontWeight.Bold
                )
                Text(
                    "Hingga 30 September 2023", fontSize = 16.sp,
                    fontFamily = urbanist
                )
                Spacer(Modifier.height(15.dp))
                Box(Modifier.wrapContentSize()) {
                    Image(painterResource(R.drawable.tren_1), null)
                    Text("", Modifier.align(Alignment.BottomStart))
                }
            }
            Spacer(Modifier.height(25.dp))
            LazyRow {
                items(filters) { filter ->
                    val paddingEnd = if (filter.name == "Tas") 20.dp else 0.dp
                    val imgPadding = if (filter.name == "Semua") 15.dp else 0.dp
                    Column(
                        Modifier
                            .padding(start = 20.dp, end = paddingEnd)
                            .clickable { navController.navigate(NavRoute.PRODUCTS.name + "/" + filter.name) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            Modifier.size(75.dp),
                            shape = CircleShape,
                            color = Color(0xFFEAECE1)
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
            Spacer(Modifier.height(25.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(14.dp), color = Color(0xFFD9D9D9)
            )
            Column(
                Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 22.dp)
            ) {
                Text(
                    "Rekomendasi Promo Pilihan", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 22.sp
                )
                Image(
                    painterResource(R.drawable.promo), "Promo",
                    Modifier.padding(top = 10.dp, bottom = 20.dp)
                )
                Text(
                    "Kejar diskon secepat kilat", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 22.sp
                )
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Text(
                        "Berakhir dalam", fontFamily = urbanist,
                        fontSize = 15.sp
                    )
                    Text(
                        "Lihat Semua", fontFamily = urbanist,
                        fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                        color = Tosca40
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEAECE1))
            ) {
                Column(Modifier.padding(22.dp, 17.dp)) {
                    Text(
                        "FLASH SALE", fontFamily = urbanist,
                        fontSize = 23.sp, fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF35C2C1)
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(fontSize = 12.sp)) {
                                append("s.d. ")
                            }
                            withStyle(SpanStyle(fontSize = 34.sp)) {
                                append("99%")
                            }
                        },
                        color = Color.Red, fontFamily = urbanist
                    )
                    Text(
                        "Untuk produk\nkesayanganmu!", fontFamily = urbanist,
                        fontWeight = FontWeight.SemiBold, fontSize = 18.sp
                    )
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(14.dp), color = Color(0xFFD9D9D9)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 17.dp), Arrangement.SpaceBetween
            ) {
                Text("Pilih Brand Favorit", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("Lihat Semua", fontFamily = urbanist,
                    fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)) {
                Surface(color = Color(0xFFF5F6FA), shape = RoundedCornerShape(14.dp)) {
                    Row(Modifier.padding(10.dp, 4.dp), verticalAlignment = Alignment.CenterVertically) {
                       Image(painterResource(R.drawable.adidas), null)
                       Text("Adidas", Modifier.padding(start = 15.dp), fontFamily = urbanist,
                           fontWeight = FontWeight.Medium, fontSize = 18.sp)
                    }
                }
                Spacer(Modifier.width(8.dp))
                Surface(color = Color(0xFFF5F6FA), shape = RoundedCornerShape(14.dp)) {
                    Row(Modifier.padding(10.dp, 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.nike), null)
                        Text("Nike", Modifier.padding(start = 15.dp), fontFamily = urbanist,
                            fontWeight = FontWeight.Medium, fontSize = 18.sp)
                    }
                }
                Spacer(Modifier.width(8.dp))
                Surface(color = Color(0xFFF5F6FA), shape = RoundedCornerShape(14.dp)) {
                    Row(Modifier.padding(10.dp, 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.fila), null)
                        Text("Fila", Modifier.padding(start = 15.dp), fontFamily = urbanist,
                            fontWeight = FontWeight.Medium, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavHostController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp, 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "", onValueChange = { },
            Modifier
                .padding(start = 10.dp, end = 15.dp)
                .width(300.dp),
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
        IconButton(
            onClick = { navController.navigate(NavRoute.NOTIFICATION.name) },
            Modifier
                .background(Dark80, CircleShape)
                .size(55.dp)
        ) {
            Icon(
                painterResource(R.drawable.notification), "Notifications",
                Modifier.size(25.dp), tint = Color.White
            )
        }
    }
}

@Composable
fun BottomBar(viewModel: MainViewModel, navController: NavHostController) {
    val homeIconTint = if (viewModel.selectedItem == "Home") Color.Black else Color(0xFF9BA5B7)
    val wishlistIconTint = if (viewModel.selectedItem == "Wishlist") Color.Black
    else Color(0xFF9BA5B7)
    val bagIconTint = if (viewModel.selectedItem == "Bag") Color.Black else Color(0xFF9BA5B7)
    val profileIconTint =
        if (viewModel.selectedItem == "Profile") Color.Black else Color(0xFF9BA5B7)

    Surface(shadowElevation = 10.dp, shape = RoundedCornerShape(25.dp, 25.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 23.dp), Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { navController.navigate(NavRoute.HOME.name) }) {
                    Icon(painterResource(R.drawable.home_05), "Beranda", tint = homeIconTint)
                }
                Text(
                    "Beranda", Modifier.offset(y = (-12).dp),
                    fontFamily = urbanist, fontSize = 14.sp, color = Color(0xFF9BA5B7)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { navController.navigate(NavRoute.WISHLIST.name) }) {
                    Icon(painterResource(R.drawable.heart), "Wishlist", tint = wishlistIconTint)
                }
                Text(
                    "Wishlist", Modifier.offset(y = (-12).dp),
                    fontFamily = urbanist, fontSize = 14.sp, color = Color(0xFF9BA5B7)
                )
            }
            Box(Modifier.padding(top = 7.dp)) {
                Surface(Modifier.size(50.dp), shape = CircleShape, color = Dark80) {
                    IconButton(onClick = { navController.navigate(NavRoute.ADD_PRODUCT.name) }) {
                        Icon(
                            Icons.Default.Add, "Add",
                            Modifier.size(40.dp), tint = Color.White
                        )
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { navController.navigate(NavRoute.BAG.name) }) {
                    Icon(painterResource(R.drawable.shopping_bag_03), "Bag", tint = bagIconTint)
                }
                Text(
                    "Keranjang", Modifier.offset(y = (-12).dp),
                    fontFamily = urbanist, fontSize = 14.sp, color = Color(0xFF9BA5B7)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { navController.navigate(NavRoute.PROFILE.name) }) {
                    Icon(painterResource(R.drawable.user_03), "Profile", tint = profileIconTint)
                }
                Text(
                    "Profil", Modifier.offset(y = (-12).dp),
                    fontFamily = urbanist,
                    fontSize = 14.sp, color = Color(0xFF9BA5B7)
                )
            }
        }
    }

}