package com.fatihsengun.repository;

import com.fatihsengun.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByShopId(UUID shopId, Pageable pageable);


    List<Product> findByIdIn(List<UUID> productIds);
    // 2. Get all products in a category (paginated)
    Page<Product> findByCategoryIdIn(List<UUID> categoryIds, Pageable pageable);
    // 3. Search products by name (case-insensitive)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
