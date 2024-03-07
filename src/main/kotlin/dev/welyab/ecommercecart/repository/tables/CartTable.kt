package dev.welyab.ecommercecart.repository.tables

import dev.welyab.ecommercecart.models.Cart
import dev.welyab.ecommercecart.models.ProductCart
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class CartTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Int? = null
    @Column
    var customerId: Int = 0
    @Column
    var productId: Int = 0
    @Column
    var quantity: Int = 0
}




fun List<CartTable>.toCart(customerId: Int) = let { entries ->
    if(entries.isEmpty()) return Cart(
        customerId = customerId,
        products = emptyList()
    )

    Cart(
        customerId = customerId,
        products = entries.map {
            ProductCart(
                productId = it.productId,
                quantity = it.quantity
            )
        }
    )
}
