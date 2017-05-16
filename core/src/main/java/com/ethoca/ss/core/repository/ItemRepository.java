package com.ethoca.ss.core.repository;

import com.ethoca.ss.core.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for generic CRUD operations for an item.
 */
public interface ItemRepository extends JpaRepository<Item, String> {
}
