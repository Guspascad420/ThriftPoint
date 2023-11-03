package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.thriftpoint.R
import com.example.thriftpoint.data.remote_source.HttpEndpoint
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.CommonTopBar
import com.example.thriftpoint.viewmodels.MainViewModel
import com.example.thriftpoint.viewmodels.ProductViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmOrder(mainViewModel: MainViewModel, productViewModel: ProductViewModel,
    navController: NavHostController, totalPrice: String?) {
    val userDataState = mainViewModel.userDataState.collectAsState()
    val formatter = DecimalFormat("#,###")

    Scaffold(
        topBar = { CommonTopBar("Konfirmasi Pemesanan", navController) },
        bottomBar = { ConfirmOrderBottomBar(totalPrice) }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            productViewModel.productsInCart.forEach { product ->
                Card(
                    Modifier.fillMaxWidth(),
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
                                .clip(RoundedCornerShape(18.dp))
                        )
                        Column(Modifier.padding(start = 15.dp)) {
                            Text(
                                product.name, fontFamily = urbanist, fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text("Rp ${formatter.format(product.price)}",
                                fontFamily = urbanist, fontSize = 20.sp)
                            Spacer(Modifier.height(40.dp))
                            Button(
                                onClick = { /*TODO*/ },
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    MaterialTheme.colorScheme.onSurface
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    "Lihat Detail", fontFamily = urbanist,
                                    fontWeight = FontWeight.SemiBold, fontSize = 17.sp
                                )
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    "Alamat Pengiriman", fontFamily = urbanist,
                    fontWeight = FontWeight.Medium, fontSize = 20.sp
                )
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
            Row(Modifier.padding(vertical = 8.dp)) {
                Image(painterResource(R.drawable.rectangle_584), "Map")
                Column(Modifier.padding(start = 12.dp)) {
                    userDataState.value.data?.let { user ->
                        Text(
                            user.data.name, fontFamily = urbanist,
                            fontWeight = FontWeight.Medium, fontSize = 17.sp
                        )
                        Text(
                            "310-823 Filkom UB", fontFamily = urbanist,
                            fontSize = 15.sp, color = Color(0xFF545F71)
                        )
                        Text(
                            "Malang, Jawa Timur", fontFamily = urbanist,
                            fontSize = 15.sp, color = Color(0xFF545F71)
                        )
                    }
                }
            }
            Spacer(Modifier.height(17.dp))
            Surface(shape = RoundedCornerShape(14.dp), border = BorderStroke(1.dp, Color.Gray)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp), Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(painterResource(R.drawable.delivery_truck), null)
                        Text(
                            "Pilih Pengiriman", Modifier.padding(start = 10.dp),
                            fontFamily = urbanist, fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Icon(Icons.Default.KeyboardArrowRight, null, tint = Color(0xFF999999))
                }
            }
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text(
                    "Metode Pembayaran", fontFamily = urbanist,
                    fontSize = 21.sp, fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(15.dp))
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
            Row {
                Image(painterResource(R.drawable.mandiri), null)
                Column(Modifier.padding(start = 10.dp)) {
                    Text(
                        "Bank Mandiri", fontFamily = urbanist,
                        fontSize = 17.sp, fontWeight = FontWeight.Medium
                    )
                    Text(
                        "**** 7690", fontFamily = urbanist,
                        fontSize = 17.sp, fontWeight = FontWeight.Medium,
                        color = Color(0xFF8F959E)
                    )
                }

            }
            Spacer(Modifier.height(35.dp))
            Text(
                "Ringkasan Belanja", fontFamily = urbanist,
                fontSize = 21.sp, fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(15.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text(
                    "Subtotal", fontFamily = urbanist,
                    fontSize = 18.sp, color = Color(0xFF8F959E)
                )
                Text(
                    "RP $totalPrice", fontFamily = urbanist,
                    fontSize = 18.sp, color = Tosca40
                )
            }
            Spacer(Modifier.height(15.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text(
                    "Biaya Pengiriman", fontFamily = urbanist,
                    fontSize = 18.sp, color = Color(0xFF8F959E)
                )
                Text(
                    "RP 0", fontFamily = urbanist,
                    fontSize = 18.sp, color = Tosca40
                )
            }
            Spacer(Modifier.height(15.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text(
                    "Total", fontFamily = urbanist,
                    fontSize = 18.sp, color = Color(0xFF8F959E),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "RP $totalPrice", fontFamily = urbanist,
                    fontSize = 18.sp, color = Tosca40,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ConfirmOrderBottomBar(totalPrice: String?) {
    Surface(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp, 25.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp), Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "Total Tagihan", fontFamily = urbanist,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    "RP $totalPrice", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, color = Tosca40
                )
            }
            Button(
                onClick = { },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(
                    "Bayar", Modifier.padding(5.dp, 3.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
