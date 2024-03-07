package dev.welyab.ecommercecart.repository.tables

import dev.welyab.ecommercecart.models.Product
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class ProductTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Int? = null
    @Column
    var name: String = ""
    @Column
    var unitPrice: BigDecimal = BigDecimal.ZERO
}

fun ProductTable.toProduct() = Product(
    id = id ?: 0,
    name = name,
    unitPrice = unitPrice
)