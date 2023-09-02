package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.ui.theme.Dark80
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.NavRoute

@Composable
fun WelcomePage(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFEAEBE3))
    ) {
        Spacer(Modifier.height(35.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(painterResource(R.drawable.img), null, Modifier.size(420.dp))
        }
        Spacer(Modifier.height(15.dp))
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(R.drawable.logo), null, Modifier.size(58.dp))
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(fontFamily = urbanist, fontWeight = FontWeight.Bold)) {
                        append("Thrift ")
                    }
                    withStyle(SpanStyle(fontFamily = urbanist)) {
                        append("Point")
                    }
                }, fontSize = 30.sp
            )
        }
        Spacer(Modifier.height(36.dp))
        Button(
            onClick = { navController.navigate(NavRoute.LOGIN.name) },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Dark80)
        ) {
            Text("Masuk", fontFamily = urbanist,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 15.dp),
                color = Color.White, fontSize = 17.sp
            )
        }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(
            onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(2.dp, Dark80)
        ) {
            Text("Daftar", fontFamily = urbanist,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 15.dp),
                color = Dark80, fontSize = 17.sp)
        }
        Spacer(Modifier.height(25.dp))
        Box(Modifier.fillMaxSize().padding(bottom = 10.dp), contentAlignment = Alignment.BottomCenter) {
            TextButton(onClick = { /*TODO*/ }) {
                Text("Masuk sebagai tamu", fontSize = 17.sp,
                    fontWeight = FontWeight.Bold, fontFamily = urbanist,
                    color = Tosca40)
            }
        }

    }
}