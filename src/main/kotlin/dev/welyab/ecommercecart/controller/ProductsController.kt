package dev.welyab.ecommercecart.controller

import dev.welyab.ecommercecart.controller.response.ProductResponse
import dev.welyab.ecommercecart.models.toProductsResponse
import dev.welyab.ecommercecart.service.ProductsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["products"])
class ProductsController(
    private val productsService: ProductsService
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<ProductResponse>> {
        return productsService
            .findAll()
            .map { it.toProductsResponse() }
            .let {
                ResponseEntity
                    .ok()
                    .body(it)
            }
    }

    @GetMapping(path = ["{id}"])
    fun find(
        @PathVariable
        id: Int
    ): ResponseEntity<ProductResponse> {
        return productsService
            .findById(id = id)
            ?.toProductsResponse()
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}
