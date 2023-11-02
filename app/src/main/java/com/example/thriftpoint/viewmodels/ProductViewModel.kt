package com.example.thriftpoint.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thriftpoint.data.datastore.DatastoreSource
import com.example.thriftpoint.data.remote_source.RemoteSource
import com.example.thriftpoint.data.remote_source.Resource
import com.example.thriftpoint.models.Product
import com.example.thriftpoint.models.response.ProductResponse
import com.example.thriftpoint.models.response.UserProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val remoteSource: RemoteSource
) : ViewModel() {
    val selectedColor by mutableStateOf("")
    val selectedSize by mutableStateOf("S")

    val allProductsState = MutableStateFlow<Resource<ProductResponse>>(Resource.Loading())
    val productsInCartState = MutableStateFlow<Resource<ProductResponse>>(Resource.Loading())
    val productsInWishlistState = MutableStateFlow<Resource<ProductResponse>>(Resource.Loading())
    val productsInWishlist = mutableSetOf<Product>()
    val productsInCart = mutableSetOf<Product>()

    fun getAllProducts() {
        viewModelScope.launch {
            remoteSource.getAllProducts().collect {
                allProductsState.value = it
            }
        }
    }

    fun getWishlist() {
        viewModelScope.launch {
            remoteSource.getWishlist().collect {
                productsInWishlistState.value = it
            }
        }
    }

    fun getAllProductsInCart() {
        viewModelScope.launch {
            remoteSource.getAllProductsInCart().collect {
                productsInCartState.value = it
            }
        }
    }

    fun addProductToWishlist(request: Product) {
        viewModelScope.launch {
            remoteSource.addProductToWishlist(request).collect {
                productsInWishlist.add(request)
            }
        }
    }

    fun addProductToCart(request: Product) {
        viewModelScope.launch {
            remoteSource.addProductToCart(request).collect {
                productsInCart.add(request)
            }
        }
    }


}