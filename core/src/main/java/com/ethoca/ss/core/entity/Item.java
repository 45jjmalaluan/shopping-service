package com.ethoca.ss.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity class for an item.
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {
    private String id;

    private Order order;

    private Product product;

    private Integer quantity;

    // Running total of product price multiplied by quantity
    private BigDecimal price;

    public Item(Product productParam, Integer quantityParam, BigDecimal priceParam) {
        setProduct(productParam);
        setQuantity(quantityParam);
        setPrice(priceParam);
    }

    /*
     * No-argument constructor
     */
    public Item() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String idParam) {
        id = idParam;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderParam) {
        order = orderParam;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productParam) {
        product = productParam;
    }

    @Column(nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantityParam) {
        quantity = quantityParam;
    }

    @Column(nullable = false, precision = 10)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal priceParam) {
        price = priceParam;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
