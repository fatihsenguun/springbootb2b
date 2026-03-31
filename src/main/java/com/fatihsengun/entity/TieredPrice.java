package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tiered_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TieredPrice extends BaseEntity {

    @Column(nullable = false)
    private Integer minQuantity;

    private Integer maxQuantity;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}