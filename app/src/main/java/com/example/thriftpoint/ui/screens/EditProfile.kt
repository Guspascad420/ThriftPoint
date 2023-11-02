package com.example.thriftpoint.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.thriftpoint.R
import com.example.thriftpoint.ui.theme.Gray40
import com.example.thriftpoint.ui.theme.Tosca40
import com.example.thriftpoint.ui.theme.urbanist
import com.example.thriftpoint.utils.CommonTopBar
import com.example.thriftpoint.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(viewModel: MainViewModel, navController: NavHostController) {
    val userDataState = viewModel.userDataState.collectAsState()

    Scaffold(topBar = { CommonTopBar("Edit profil", navController)}) {
        Column(Modifier.padding(it)) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(R.drawable.foto_profil), null)
                Text("Edit", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 17.sp,
                    color = Tosca40)
            }
            Spacer(Modifier.height(15.dp))
            Text("Nama", fontFamily = urbanist,
                fontWeight = FontWeight.Bold, fontSize = 17.sp)
            userDataState.value.data?.let { user ->
                OutlinedTextField(
                    value = user.data.name,
                    onValueChange = { },
                    Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    )
                )
                Spacer(Modifier.height(8.dp))
                Text("No. Telepon", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 17.sp)
//                OutlinedTextField(
//                    value = viewModel.user.phone_number,
//                    onValueChange = {  },
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 10.dp),
//                    textStyle = TextStyle(
//                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
//                    ),
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        containerColor = Color(0xFFF7F8F9),
//                        unfocusedBorderColor = Color(0xFFE8ECF4)
//                    )
//                )
                Spacer(Modifier.height(8.dp))
                Text("Email", fontFamily = urbanist,
                    fontWeight = FontWeight.Bold, fontSize = 17.sp)
                OutlinedTextField(
                    value = user.data.email,
                    onValueChange = {  },
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = urbanist, fontWeight = FontWeight.SemiBold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF7F8F9),
                        unfocusedBorderColor = Color(0xFFE8ECF4)
                    )
                )
            }
            Spacer(Modifier.height(15.dp))
            Button(onClick = { }, Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                Text("Simpan Perubahan", fontFamily = urbanist,
                    fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            }
        }
    }
}