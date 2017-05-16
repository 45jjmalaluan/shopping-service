package com.ethoca.ss.core.repository

import com.ethoca.ss.core.entity.Product
import com.ethoca.ss.web.config.PersistenceConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

/**
 * Unit test for product CRUD operations.
 */
@TestPropertySource(["classpath:application.properties"])
@ContextConfiguration(classes = [PersistenceConfig.class])
@Transactional
class ProductRepositoryTest extends Specification {
    @Autowired
    ProductRepository productRepository

    def "Find all products"() {
        when: "using the Repository"
        List<Product> products = productRepository.findAll()
        then: "products are found"
        products != null
        products.size() > 0
    }

    def "Find existing product"() {
        when: "using the Repository"
        Product product = productRepository.findOne("3f04d2ab-3850-4004-bc43-a72ee2a2bad0")
        then: "product is found"
        product.description.equalsIgnoreCase("Samsung Galaxy S8")
        product.price == 1149.95
    }

    def "Manipulate product"() {
        when: "Add new product using the Repository"
        Product product = productRepository.save(new Product(description: "Sony Xperia XZ", price: 895.71))
        String productId = product.id
        then: "product created"
        product != null

        when: "Editing the product using the Repository"
        product.description = "Google Pixel"
        product.price = 899.99
        productRepository.saveAndFlush(product)
        then: "product changed"
        product.id.equals(productId)
        product.description.equalsIgnoreCase("Google Pixel")
        product.price == 899.99

        when: "Remove product using the Repository"
        productRepository.delete(productId)
        then: "product is not found"
        !productRepository.exists(productId)
    }
}
