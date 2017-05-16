package com.ethoca.ss.core.repository;

import com.ethoca.ss.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for generic CRUD operations for an order.
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
