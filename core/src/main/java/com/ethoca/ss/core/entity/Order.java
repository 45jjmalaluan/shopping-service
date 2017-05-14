package com.ethoca.ss.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {
    private String id;

    private Calendar time;

    private BigDecimal total;

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

    @Column(nullable = false)
    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar timeParam) {
        time = timeParam;
    }

    @Column(nullable = false, precision = 10)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal totalParam) {
        total = totalParam;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> itemsParam) {
        items = itemsParam;
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
