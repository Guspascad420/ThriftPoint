package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.thriftpoint.ui.theme.Dark80
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.CommonTopBar
import com.example.thriftpoint.utils.dashedBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduct(navController: NavHostController) {
    Scaffold(
        topBar = { CommonTopBar("Tambah Produk", navController) },
        bottomBar = { AddProductBottomBar() }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    "Yuk, isi informasi produkmu!",
                    Modifier.padding(vertical = 18.dp), fontFamily = urbanist,
                    fontWeight = FontWeight.Medium, fontSize = 22.sp
                )
                Text(
                    "Foto Produk", fontFamily = urbanist,
                    fontWeight = FontWeight.Medium, fontSize = 19.sp
                )
            }
            Spacer(Modifier.height(15.dp))
            LazyRow {
                item {
                    Column(
                        Modifier.padding(start = 20.dp, end = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { /*TODO*/ }, shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFD9D9D9))
                        ) {
                            Icon(
                                Icons.Default.Add, "Add Photo",
                                Modifier
                                    .padding(17.dp, 33.dp)
                                    .size(50.dp), Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            "Utama", fontFamily = urbanist,
                            fontWeight = FontWeight.Medium, fontSize = 17.sp
                        )
                    }
                }
                items(4) {
                    Column(
                        Modifier.padding(end = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            Modifier.dashedBorder(3.dp, Tosca40, 8.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                Icons.Default.Add, "Add Photo",
                                Modifier
                                    .padding(38.dp, 42.dp)
                                    .size(50.dp), Tosca40
                            )
                        }
                        Text(
                            "$it", fontFamily = urbanist,
                            fontWeight = FontWeight.Medium, fontSize = 17.sp
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    "Detail Produk", fontFamily = urbanist,
                    fontWeight = FontWeight.Medium, fontSize = 19.sp
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    placeholder = {
                        Text(
                            "Judul",
                            fontFamily = urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray40
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    )
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    placeholder = {
                        Text(
                            "Harga",
                            fontFamily = urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray40
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    )
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    placeholder = {
                        Text(
                            "Kategori",
                            fontFamily = urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray40
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    )
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .padding(bottom = 10.dp),
                    placeholder = {
                        Text(
                            "Deskripsi",
                            fontFamily = urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray40
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    )
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "Opsi Produk", fontFamily = urbanist,
                    fontWeight = FontWeight.Medium, fontSize = 19.sp
                )
                Spacer(Modifier.height(10.dp))
                Row {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        Modifier.width(127.dp),
                        placeholder = {
                            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                                Text(
                                    "Berat",
                                    fontFamily = urbanist,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Gray40
                                )
                                Text(
                                    "gram",
                                    fontFamily = urbanist,
                                    fontWeight = FontWeight.Bold,
                                    color = Gray40
                                )
                            }

                        },
                        textStyle = TextStyle(
                            fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFF7F8F9),
                            unfocusedBorderColor = Color(0xFFE8ECF4)
                        )
                    )
                    Spacer(Modifier.width(7.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        Modifier.width(192.dp),
                        placeholder = {
                            Text(
                                "Stok tersedia",
                                fontFamily = urbanist,
                                fontWeight = FontWeight.SemiBold,
                                color = Gray40
                            )
                        },
                        textStyle = TextStyle(
                            fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFF7F8F9),
                            unfocusedBorderColor = Color(0xFFE8ECF4)
                        )
                    )
                }
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    placeholder = {
                        Text(
                            "Varian",
                            fontFamily = urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray40
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    ),
                    trailingIcon = {
                        Icon(Icons.Default.Add, "Add")
                    }
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    placeholder = {
                        Text(
                            "Pengiriman",
                            fontFamily = urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Gray40
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    ),
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, "Arrow Dropdown")
                    }
                )
            }
        }
    }
}

@Composable
fun AddProductBottomBar() {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
        OutlinedButton(
            onClick = { },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Tosca40)
        ) {
            Text(
                "Simpan", fontFamily = urbanist,
                fontWeight = FontWeight.Medium, fontSize = 20.sp,
                color = Tosca40
            )
        }
        Button(
            onClick = { }, shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Dark80)
        ) {
            Text(
                "Terbitkan", Modifier.padding(5.dp, 10.dp),
                fontFamily = urbanist, fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
    }
}