package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.ui.theme.Dark80
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.Gray80
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.NavRoute
import com.example.thriftpoint.utils.NoRippleInteractionSource
import com.example.thriftpoint.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: AuthViewModel = viewModel()
    val allNotFilled = remember {
        derivedStateOf {
            viewModel.email.text.isEmpty() && viewModel.password.text.isEmpty()
        }
    }
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
            "Selamat Datang!", fontFamily = urbanist,
            fontWeight = FontWeight.Bold, fontSize = 32.sp,
        )
        Text(
            "Silahkan Masuk", fontFamily = urbanist,
            fontWeight = FontWeight.Bold, fontSize = 32.sp,
        )
        Spacer(Modifier.height(30.dp))
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            Modifier
                .fillMaxWidth(),
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
            ),
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.isPasswordVisible = !viewModel.isPasswordVisible
                }) {
                    Icon(
                        if (viewModel.isPasswordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff, null
                    )
                }
            },
            textStyle = TextStyle(
                fontFamily = urbanist, fontWeight = FontWeight.SemiBold
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFF7F8F9),
                unfocusedBorderColor = Color(0xFFE8ECF4)
            ),
            placeholder = {
                Text(
                    "Masukkan password anda",
                    fontFamily = urbanist,
                    fontWeight = FontWeight.SemiBold,
                    color = Gray40
                )
            },
            visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            if (viewModel.error) {
                Text("Email / Password salah", fontFamily = urbanist,
                    color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
            TextButton(
                onClick = { navController.navigate(NavRoute.FORGOT_PASSWORD.name) },
                interactionSource = NoRippleInteractionSource(),
            ) {
                Text(
                    "Lupa Password?", fontFamily = urbanist,
                    fontWeight = FontWeight.SemiBold, fontSize = 16.sp,
                    color = Gray80, textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Button(
            onClick = { navController.navigate(NavRoute.HOME.name) },
            Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Dark80),
            enabled = !allNotFilled.value
        ) {
            Text(
                "Masuk", fontFamily = urbanist,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 15.dp),
                color = Color.White, fontSize = 17.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 20.dp)
                .fillMaxWidth()
        ) {
            Divider(
                Modifier
                    .width(100.dp)
                    .padding(end = 10.dp), color = Color(0xFFE8ECF4)
            )
            Text(
                "Atau Masuk Dengan", fontFamily = urbanist,
                fontWeight = FontWeight.SemiBold, fontSize = 17.sp,
                color = Gray80
            )
            Divider(
                Modifier
                    .width(100.dp)
                    .padding(start = 10.dp), color = Color(0xFFE8ECF4)
            )
        }
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFE8ECF4))
            ) {
                Image(
                    painterResource(R.drawable.google_ic), contentDescription = "Google",
                    Modifier.padding(15.dp, 10.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFE8ECF4))
            ) {
                Image(
                    painterResource(R.drawable.facebook_ic), contentDescription = "Facebook",
                    Modifier.padding(15.dp, 10.dp)
                )
            }
        }
        Column(Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.CenterHorizontally) {
            Text(
                "Belum memiliki akun?", fontFamily = urbanist,
                fontWeight = FontWeight.Medium, fontSize = 17.sp
            )
            TextButton(
                onClick = { navController.navigate(NavRoute.SIGNUP.name) },
                interactionSource = NoRippleInteractionSource()
            ) {
                Text(
                    "Daftar Sekarang", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 17.sp,
                    color = Tosca40
                )
            }
        }
    }
}