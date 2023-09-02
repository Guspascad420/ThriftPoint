package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.thriftpoint.ui.theme.Dark80
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.utils.NoRippleInteractionSource
import com.example.thriftpoint.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(navController: NavHostController) {
    val viewModel: AuthViewModel = viewModel()
    Column(Modifier.padding(20.dp, 10.dp)) {
        Spacer(Modifier.height(20.dp))
        Surface(
            Modifier.size(50.dp),
            border = BorderStroke(1.dp, Color(0xFFE8ECF4)),
            shape = RoundedCornerShape(15.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBackIos, "Back Button")
            }
        }
        Spacer(Modifier.height(30.dp))
        Text(
            "Lupa Password?", fontFamily = urbanist,
            fontWeight = FontWeight.Bold, fontSize = 32.sp,
        )
        Spacer(Modifier.height(20.dp))
        Text(
            "Jangan khawatir! Silakan masukkan alamat email yang tertaut dengan akun Anda",
            fontFamily = urbanist, fontSize = 16.sp,
            color = Gray40
        )
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp),
            placeholder = {
                Text(
                    "Masukkan email anda",
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
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Dark80)
        ) {
            Text(
                "Kirim kode verifikasi", fontFamily = urbanist,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 15.dp),
                color = Color.White, fontSize = 17.sp
            )
        }
        Column(Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.CenterHorizontally) {
            Text(
                "Ingat Password?", fontFamily = urbanist,
                fontWeight = FontWeight.Medium, fontSize = 17.sp
            )
            TextButton(
                onClick = { navController.navigate(NavRoute.SIGNUP.name) },
                interactionSource = NoRippleInteractionSource()
            ) {
                Text(
                    "Masuk", Modifier.offset(y = (-5).dp), fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 17.sp,
                    color = Tosca40
                )
            }

        }
    }
}