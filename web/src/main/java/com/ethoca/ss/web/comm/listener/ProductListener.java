package com.ethoca.ss.web.comm.listener;

import com.ethoca.ss.core.entity.Product;
import com.ethoca.ss.core.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
@RestController
@RequestMapping("/v1/products")
public class ProductListener {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Product> getOne(@PathVariable("productId") String productId) {
        Product product = productRepository.getOne(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
