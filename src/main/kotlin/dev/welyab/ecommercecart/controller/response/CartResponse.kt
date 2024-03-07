package dev.welyab.ecommercecart.controller.response

import dev.welyab.ecommercecart.models.Cart
import dev.welyab.ecommercecart.models.Product
import dev.welyab.ecommercecart.models.ProductCart
import java.math.BigDecimal

data class CartResponse(
    val customerId: Int,
    val products: List<ProductCartResponse>,
    val totalPrice: BigDecimal
)

data class ProductCartResponse(
    val product: ProductResponse,
    val quantity: Int,
    val totalPrice: BigDecimal,
)

fun createCartResponse(
    cart: Cart,
    products: List<Product>
): CartResponse {
    val productCartsResponse = cart.products.map { productCart ->
        val product = products.first { it.id == productCart.productId }
        ProductCartResponse(
            product = ProductResponse(
                id = product.id,
                name = product.name,
                unitPrice = product.unitPrice
            ),
            quantity = productCart.quantity,
            totalPrice = productCart.quantity.toBigDecimal() * product.unitPrice,
        )
    }
    return CartResponse(
        customerId = cart.customerId,
        products = productCartsResponse,
        totalPrice = productCartsResponse.sumOf { it.totalPrice }
    )
}