package com.fatihsengun.dto;

import com.fatihsengun.entity.BaseEntity;
import com.fatihsengun.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrder extends BaseEntity {


    private String orderNumber;

    private UUID buyerId;
    private String buyerFirstName;
    private String buyerLastName;

    private UUID shopId;
    private String shopName;

    private BigDecimal totalAmount;
    private OrderStatus status;

    private List<DtoOrderItem> items = new ArrayList<>();
}