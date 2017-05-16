package com.ethoca.ss.core.repository;

import com.ethoca.ss.core.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for generic CRUD operations for a cart.
 */
public interface CartRepository extends JpaRepository<Cart, String> {
}
