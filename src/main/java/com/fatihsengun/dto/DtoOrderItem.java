package com.fatihsengun.dto;

import com.fatihsengun.entity.BaseEntity;
import com.fatihsengun.entity.Order;
import com.fatihsengun.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItem extends BaseEntity {

    private UUID productId;
    private String productName;

    private Integer quantity;
    private BigDecimal priceAtPurchase;
}
