package dev.welyab.ecommercecart.service

import dev.welyab.ecommercecart.models.Cart
import dev.welyab.ecommercecart.repository.CartRepository
import dev.welyab.ecommercecart.repository.tables.CartTable
import dev.welyab.ecommercecart.repository.tables.toCart
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Service

@Service
class CartsService(
    private val cartRepository: CartRepository,
) {

    fun findCart(customerId: Int): Cart {
        return cartRepository
            .findByCustomerId(customerId = customerId)
            .toCart(customerId = customerId)
    }

    fun addProduct(customerId: Int, productId: Int, quantity: Int) {
        cartRepository
            .findByCustomerIdAndProductId(
                customerId = customerId,
                productId = productId
            )
            .getOrNull()
            ?.let {
                it.quantity += quantity
            }
            ?: CartTable().apply {
                this.customerId = customerId
                this.productId = productId
                this.quantity = quantity
            }
            .also {
                cartRepository.saveAndFlush(it)
            }
    }
}
