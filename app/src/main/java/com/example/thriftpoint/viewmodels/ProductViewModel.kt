package com.example.thriftpoint.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {
    val selectedColor by mutableStateOf("")
    val selectedSize by mutableStateOf("S")
}