package dev.welyab.ecommercecart.repository

import dev.welyab.ecommercecart.repository.tables.ProductTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductTable, Int>
