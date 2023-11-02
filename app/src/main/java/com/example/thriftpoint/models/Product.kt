package com.example.thriftpoint.models

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val name: String,
    @SerializedName("image_res")
    val imageRes: String,
    val price: Int,
)
