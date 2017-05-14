package com.ethoca.ss.core.service

import com.ethoca.ss.core.entity.Cart
import com.ethoca.ss.core.entity.Order
import com.ethoca.ss.core.entity.Product
import com.ethoca.ss.core.repository.CartRepository
import com.ethoca.ss.core.repository.ProductRepository
import com.ethoca.ss.web.config.PersistenceConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

/**
 *
 */
@TestPropertySource(["classpath:application.properties"])
@ContextConfiguration(classes = [PersistenceConfig.class, CartServiceImpl.class])
@Transactional
class CartServiceTest extends Specification {
    @Autowired
    CartRepository cartRepository

    @Autowired
    CartService cartService

    @Autowired
    ProductRepository productRepository

    String cartId, productId1, productId2

    BigDecimal price1, price2

    def setup() {
        Cart newCart = cartRepository.save(new Cart())
        cartId = newCart.id
        Product product = productRepository.getOne("3f04d2ab-3850-4004-bc43-a72ee2a2bad0")
        productId1 = product.id
        price1 = product.price
        product = productRepository.getOne("a0add0bb-6153-4c72-852f-5a8dd0816b9a")
        productId2 = product.id
        price2 = product.price
    }

    def "Add product to cart"() {
        when: "using the Service"
        cartService.addProduct(cartId, productId1, 1)
        cartService.addProduct(cartId, productId2, 2)
        then: "cart has items"
        Cart cart = cartRepository.getOne(cartId)
        cart.items != null
        cart.items.size() > 0
        and: "cart total item amount is correct"
        BigDecimal totalAmount = (price1 * 1) + (price2 * 2)
        cart.calculateTotal() == totalAmount
    }

    def "Remove product from cart"() {
        setup: "using the Service to add products to cart"
        cartService.addProduct(cartId, productId1, 1)
        cartService.addProduct(cartId, productId2, 2)

        when: "cart has items"
        Cart cart = cartRepository.getOne(cartId)
        then: "cart total item amount is correct"
        cart.calculateTotal() == (price1 * 1) + (price2 * 2)

        when: "using the Service to remove product from cart"
        cartService.removeProduct(cartId, productId1)
        cart = cartRepository.getOne(cartId)
        then: "cart still have an item"
        cart.items != null
        cart.items.size() > 0
        and: "cart total item amount is correct"
        cart.calculateTotal() == (price2 * 2)
    }

    def "Place the order"() {
        setup: "using the Service to add products to cart"
        cartService.addProduct(cartId, productId1, 1)
        cartService.addProduct(cartId, productId2, 2)

        when: "using the Service to create and order"
        Order order = cartService.createOrder(cartId)
        then: "order created"
        order.total == (price1 * 1) + (price2 * 2)
    }
}
