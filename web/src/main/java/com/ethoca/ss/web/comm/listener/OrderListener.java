package com.ethoca.ss.web.comm.listener;

import com.ethoca.ss.core.entity.Order;
import com.ethoca.ss.core.repository.OrderRepository;
import com.ethoca.ss.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/v1/orders")
public class OrderListener {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Order> getOne(@PathVariable("orderId") String orderId) {
        Order order = orderRepository.getOne(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{cartId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Order> createOrder(@PathVariable("cartId") String cartId) {
        Order order = cartService.createOrder(cartId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
