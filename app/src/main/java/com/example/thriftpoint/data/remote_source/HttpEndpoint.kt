package com.example.thriftpoint.data.remote_source

object HttpEndpoint {
    const val BASE_URL = "http://192.168.18.42:8080/api"
    const val IMG_BASE_URL = "https://guspascad.blob.core.windows.net/democontainer/"
    const val USER = "$BASE_URL/user"
    const val SIGNUP = "$USER/register"
    const val LOGIN = "$USER/login"
    const val PROFILE = "$USER/profile"
    const val PRODUCTS = "$BASE_URL/products"
    const val ADD_PRODUCTS_IN_CART = "$USER/carts/add"
    const val REMOVE_PRODUCTS_IN_CART = "$USER/carts/remove"
    const val GET_PRODUCTS_IN_CART = "$USER/carts"
    const val WISHLIST = "$USER/wishlists"
    const val ADD_WISHLIST = "$WISHLIST/add"
    const val REMOVE_WISHLIST = "$WISHLIST/remove"
}