package com.ethoca.ss.core.service;

import com.ethoca.ss.core.entity.Cart;
import com.ethoca.ss.core.entity.Order;

/**
 *
 */
public interface CartService {
    Cart addProduct(String cartId, String productId, Integer quantity);

    void removeProduct(String cartId, String productId);

    Cart updateProduct(String cartId, String productId, Integer quantity);

    Order createOrder(String cartId);
}
