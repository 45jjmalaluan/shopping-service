package com.ethoca.ss.web.comm.listener;

import com.ethoca.ss.core.entity.Cart;
import com.ethoca.ss.core.repository.CartRepository;
import com.ethoca.ss.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

/**
 *
 */
@Transactional
@RestController
@RequestMapping("/v1/carts")
public class CartListener {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Cart>> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Cart> getOne(@PathVariable("cartId") String cartId) {
        Cart cart = cartRepository.findOne(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Cart> create(@RequestBody Cart cartReq, HttpServletRequest request) {
        Cart cart = cartRepository.save(cartReq);
        HttpHeaders header = new HttpHeaders();
        header.setLocation(URI.create(request.getRequestURL() + "/" + cart.getId()));
        return new ResponseEntity<>(cart, header, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{cartId}/product/{productId}/quantity/{quantity}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> addProduct(
        @PathVariable("cartId") String cartId,
        @PathVariable("productId") String productId,
        @PathVariable("quantity") Integer quantity) {
        cartService.addProduct(cartId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{cartId}/product/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProduct(
        @PathVariable("cartId") String cartId,
        @PathVariable("productId") String productId) {
        cartService.removeProduct(cartId, productId);
    }
}
