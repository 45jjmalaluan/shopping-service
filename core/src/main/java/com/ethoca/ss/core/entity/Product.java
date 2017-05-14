package com.ethoca.ss.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private String id;

    private String description;

    private BigDecimal price;

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

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionParam) {
        description = descriptionParam;
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
