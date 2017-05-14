package com.ethoca.ss.core.repository;

import com.ethoca.ss.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface ProductRepository extends JpaRepository<Product, String> {
}
