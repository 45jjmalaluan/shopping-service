package com.ethoca.ss.core.repository;

import com.ethoca.ss.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for generic CRUD operations for a product.
 */
public interface ProductRepository extends JpaRepository<Product, String> {
}
