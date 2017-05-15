package com.ethoca.ss.core.repository;

import com.ethoca.ss.core.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface ItemRepository extends JpaRepository<Item, String> {
}
