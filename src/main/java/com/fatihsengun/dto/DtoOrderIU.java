package com.fatihsengun.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrderIU {

    @Valid
    @NotEmpty(message = "Your cart is empty. Please add items before checking out.")
    private List<DtoOrderItemIU> items;

}