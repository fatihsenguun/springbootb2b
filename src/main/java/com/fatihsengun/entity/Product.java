package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String category;

    // Concurrency Control: Optimistic Locking
    // Prevents race conditions during high-volume B2B checkouts
    @Version
    private Long version;

    // 1. A Shop owns the Product (Standard Users cannot own products)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_profile_id", nullable = false)
    private BusinessProfile shop;

    // 2. A Product has many price tiers (e.g., Buy 100 for $5, Buy 500 for $4)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TieredPrice> tieredPrices = new ArrayList<>();
}