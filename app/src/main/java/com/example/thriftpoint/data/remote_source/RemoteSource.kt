package com.example.thriftpoint.data.remote_source

import com.example.thriftpoint.models.Product
import com.example.thriftpoint.models.request.LoginRequest
import com.example.thriftpoint.models.request.SignUpRequest
import com.example.thriftpoint.models.response.AuthResponse
import com.example.thriftpoint.models.response.MetaResponse
import com.example.thriftpoint.models.response.ProductResponse
import com.example.thriftpoint.models.response.UserProfileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val client: HttpClient
) {
    fun signUp(request: SignUpRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.SIGNUP)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun login(request: LoginRequest) = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getUserData() = getResponse {
        val res = client.get {
            url(HttpEndpoint.PROFILE)
            contentType(ContentType.Application.Json)
        }.body<UserProfileResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getAllProducts() = getResponse {
        val res = client.get {
            url(HttpEndpoint.PRODUCTS)
            contentType(ContentType.Application.Json)
        }.body<ProductResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getAllProductsInCart() = getResponse {
        val res = client.get {
            url(HttpEndpoint.GET_PRODUCTS_IN_CART)
            contentType(ContentType.Application.Json)
        }.body<ProductResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getWishlist() = getResponse {
        val res = client.get {
            url(HttpEndpoint.WISHLIST)
            contentType(ContentType.Application.Json)
        }.body<ProductResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun addProductToWishlist(request: Product) = getResponse {
        val res = client.post {
            url(HttpEndpoint.ADD_WISHLIST)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<MetaResponse>()

        if (res.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.message)
        }
    }

    fun removeProductFromWishlist(request: Product) = getResponse {
        val res = client.delete {
            url(HttpEndpoint.REMOVE_WISHLIST)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<MetaResponse>()

        if (res.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.message)
        }
    }

    fun addProductToCart(request: Product) = getResponse {
        val res = client.post {
            url(HttpEndpoint.ADD_PRODUCTS_IN_CART)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<MetaResponse>()

        if (res.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.message)
        }
    }

    fun removeProductFromCart(request: Product) = getResponse {
        val res = client.delete {
            url(HttpEndpoint.REMOVE_PRODUCTS_IN_CART)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<MetaResponse>()

        if (res.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.message)
        }
    }

}