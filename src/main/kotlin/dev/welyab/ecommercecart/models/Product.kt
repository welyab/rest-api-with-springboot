package dev.welyab.ecommercecart.models

import dev.welyab.ecommercecart.controller.response.ProductResponse
import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val unitPrice: BigDecimal
)

fun Product.toProductsResponse() = ProductResponse(
    id = id,
    name = name,
    unitPrice = unitPrice
)