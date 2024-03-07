package dev.welyab.ecommercecart.service

import dev.welyab.ecommercecart.models.Product
import dev.welyab.ecommercecart.repository.ProductRepository
import dev.welyab.ecommercecart.repository.tables.toProduct
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Service

@Service
class ProductsService(
    private val productRepository: ProductRepository
) {

    fun findAll(): List<Product> {
        return productRepository
            .findAll()
            .map { it.toProduct() }
    }

    fun findById(id: Int): Product? {
        return productRepository
            .findById(id)
            .map { it.toProduct() }
            .getOrNull()
    }

    fun findByIds(ids: List<Int>): List<Product> {
        return productRepository
            .findAllById(ids)
            .map { it.toProduct() }
    }
}
