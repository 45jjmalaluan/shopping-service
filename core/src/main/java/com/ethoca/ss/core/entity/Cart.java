package com.ethoca.ss.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    private String id;

    private List<Item> items = new ArrayList<>();

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "cart_item",
        joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")}
    )
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> itemsParam) {
        items = itemsParam;
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : getItems()) {
            BigDecimal value = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            total = total.add(value);
        }
        return total;
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
