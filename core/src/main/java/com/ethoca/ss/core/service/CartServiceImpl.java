package com.ethoca.ss.core.service;

import com.ethoca.ss.core.entity.Cart;
import com.ethoca.ss.core.entity.Item;
import com.ethoca.ss.core.entity.Order;
import com.ethoca.ss.core.entity.Product;
import com.ethoca.ss.core.repository.CartRepository;
import com.ethoca.ss.core.repository.OrderRepository;
import com.ethoca.ss.core.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addProduct(String cartId, String productId, Integer quantity) {
        Cart cart = cartRepository.findOne(cartId);
        Product product = productRepository.findOne(productId);
        cart.getItems().add(new Item(product, quantity, product.getPrice(), cart));
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void removeProduct(String cartId, String productId) {
        Cart cart = cartRepository.findOne(cartId);
        for (Item item : cart.getItems()) {
            String itemProductId = item.getProduct().getId();
            if (StringUtils.equals(productId, itemProductId)) {
                cart.getItems().remove(item);
            }
        }
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public Order createOrder(String cartId) {
        Cart cart = cartRepository.findOne(cartId);
        Order order = new Order();
        order.setTime(Calendar.getInstance());
        order.setTotal(cart.calculateTotal());
        List<Item> items = new ArrayList<>();
        items.addAll(cart.getItems());
        order.setItems(items);
        return orderRepository.save(order);
    }
}
