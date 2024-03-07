package dev.welyab.ecommercecart.controller

import dev.welyab.ecommercecart.controller.request.AddProductCartResquest
import dev.welyab.ecommercecart.controller.request.ProductCartRequest
import dev.welyab.ecommercecart.controller.response.CartResponse
import dev.welyab.ecommercecart.controller.response.createCartResponse
import dev.welyab.ecommercecart.service.CartsService
import dev.welyab.ecommercecart.service.ProductsService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["carts/customer/{customerId}"])
class CartsController(
    val cartsService: CartsService,
    val productsService: ProductsService
) {

    @GetMapping
    fun findCart(
        @PathVariable("customerId")
        customerId: Int
    ): ResponseEntity<CartResponse> {
        val cart = cartsService.findCart(customerId = customerId)
        val products  = productsService
            .findByIds( cart.products.map { it.productId }.toList() )
        return ResponseEntity.ok(
            createCartResponse(
                cart = cart,
                products = products
            )
        )
    }

    @PostMapping
    fun addProduct(
        @PathVariable("customerId")
        customerId: Int,
        @RequestBody
        addProductRequest: AddProductCartResquest
    ): ResponseEntity<CartResponse> {
        cartsService.addProduct(
            customerId = customerId,
            productId = addProductRequest.productId,
            quantity = addProductRequest.quantity
        )
        val cart = cartsService.findCart(customerId = customerId)
        val products  = productsService
            .findByIds( cart.products.map { it.productId }.toList() )
        return ResponseEntity.ok(
            createCartResponse(
                cart = cart,
                products = products
            )
        )
    }
}
