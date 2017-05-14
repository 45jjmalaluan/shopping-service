package com.ethoca.ss.core.service;

import com.ethoca.ss.core.entity.Order;

/**
 *
 */
public interface CartService {
    void addProduct(String cartId, String productId, Integer quantity);

    void removeProduct(String cartId, String productId);

    Order createOrder(String cartId);
}
