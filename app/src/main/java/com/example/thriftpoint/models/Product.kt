package com.example.thriftpoint.models

data class Product(
    val name: String,
    val img: Int,
    val price: String,
    val isFavorite: Boolean
)
