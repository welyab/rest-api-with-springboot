package dev.welyab.ecommercecart.controller.response

import java.math.BigDecimal

data class ProductResponse(
    val id: Int,
    val name: String,
    val unitPrice: BigDecimal
)
