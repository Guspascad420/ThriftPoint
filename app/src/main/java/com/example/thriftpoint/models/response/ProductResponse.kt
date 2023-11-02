package com.example.thriftpoint.models.response

import com.example.thriftpoint.models.Product

data class ProductResponse(
    val meta: MetaResponse,
    val data: List<Product>
)