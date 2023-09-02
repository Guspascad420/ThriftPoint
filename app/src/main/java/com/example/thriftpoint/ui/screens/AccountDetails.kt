package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.CommonTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetails(navController: NavHostController) {
    Scaffold(topBar = { CommonTopBar("Akun Saya", navController) }) {
        Column(Modifier.padding(it)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 8.dp), Arrangement.SpaceBetween) {
                Row {
                    Image(painterResource(R.drawable.foto_profil), null)
                    Column(Modifier.padding(start = 4.dp)) {

                    }
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(R.drawable.edit_05), "Edit", tint = Tosca40)
                }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)) {
                Icon(Icons.Default.List, null)
                Text("Daftar Transaksi", Modifier.padding(start = 10.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp)
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), color = Color(0xFF9BA5B7))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)) {
                Icon(Icons.Default.LocationOn, null)
                Text("Alamat Pengiriman", Modifier.padding(start = 10.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp)
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), color = Color(0xFF9BA5B7))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)) {
                Icon(Icons.Default.CreditCard, null)
                Text("Rekening Pembayaran", Modifier.padding(start = 10.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp)
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), color = Color(0xFF9BA5B7))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)) {
                Icon(painterResource(R.drawable.book_open), null)
                Text("Katalog Saya", Modifier.padding(start = 10.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp)
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), color = Color(0xFF9BA5B7))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)) {
                Icon(Icons.Default.Logout, null)
                Text("Keluar", Modifier.padding(start = 10.dp),
                    fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp)
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), color = Color(0xFF9BA5B7))
        }
    }
}