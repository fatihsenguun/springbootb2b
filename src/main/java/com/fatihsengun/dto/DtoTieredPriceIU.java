package com.fatihsengun.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoTieredPriceIU {

    @NotNull(message = "Minimum quantity is required")
    private Integer minQuantity;

    private Integer maxQuantity;

    @NotNull(message = "Unit price is required")
    private BigDecimal unitPrice;

}
