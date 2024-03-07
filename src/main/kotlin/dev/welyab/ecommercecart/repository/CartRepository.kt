package dev.welyab.ecommercecart.repository

import dev.welyab.ecommercecart.repository.tables.CartTable
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<CartTable, Int> {
    fun findByCustomerId(customerId: Int): List<CartTable>
    fun findByCustomerIdAndProductId(customerId: Int, productId: Int): Optional<CartTable>
}