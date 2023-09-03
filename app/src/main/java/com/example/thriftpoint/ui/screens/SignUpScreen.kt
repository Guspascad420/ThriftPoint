package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.thriftpoint.viewmodels.AuthUiState
import com.example.thriftpoint.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController) {
    val viewModel: AuthViewModel = viewModel()
    LaunchedEffect(key1 = viewModel.authUiState) {
        if (viewModel.authUiState == AuthUiState.Success) {
            navController.navigate(NavRoute.HOME.name)
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
        Spacer(Modifier.height(20.dp))
        Text(
            "Halo Dunia!", fontFamily = urbanist,
            fontWeight = FontWeight.Bold, fontSize = 32.sp,
        )
        Text(
            "Daftar Untuk Memulai", fontFamily = urbanist,
            fontWeight = FontWeight.Bold, fontSize = 32.sp,
        )
        Spacer(Modifier.height(30.dp))
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            Modifier
                .fillMaxWidth().padding(bottom = 10.dp),
            placeholder = {
                Text(
                    "Nama",
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
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            Modifier
                .fillMaxWidth().padding(bottom = 10.dp),
            placeholder = {
                Text(
                    "Email",
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
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            Modifier
                .fillMaxWidth().padding(bottom = 10.dp),
            placeholder = {
                Text(
                    "Password",
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
            visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None
                                   else PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = viewModel.passwordConfirm,
            onValueChange = { viewModel.passwordConfirm = it },
            Modifier.fillMaxWidth().padding(bottom = 10.dp),
            placeholder = {
                Text(
                    "Konfirmasi Password",
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
            visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None
                                   else PasswordVisualTransformation()
        )
        Button(
            onClick = { viewModel.handleSignUp() },
            Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Dark80)
        ) {
            Text(
                "Daftar", fontFamily = urbanist,
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
                "Atau Daftar Dengan", fontFamily = urbanist,
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
                border = BorderStroke(1.dp, Color(0xFFE8ECF4))) {
                Image(
                    painterResource(R.drawable.google_ic), contentDescription = "Google",
                    Modifier.padding(15.dp, 10.dp))
            }
            Spacer(Modifier.width(10.dp))
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFE8ECF4))) {
                Image(
                    painterResource(R.drawable.facebook_ic), contentDescription = "Facebook",
                    Modifier.padding(15.dp, 10.dp))
            }
        }
        Column(Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.CenterHorizontally) {
            Text(
                "Sudah memiliki akun?", fontFamily = urbanist,
                fontWeight = FontWeight.Medium, fontSize = 17.sp
            )
            TextButton(onClick = { navController.navigate(NavRoute.LOGIN.name) }) {
                Text(
                    "Masuk Sekarang", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 17.sp,
                    color = Tosca40
                )
            }

        }
    }
}