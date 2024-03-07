package dev.welyab.ecommercecart.models

data class Cart(
    val customerId: Int,
    val products: List<ProductCart>
)
