package com.fatihsengun.dto;

import com.fatihsengun.entity.BaseEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoProduct  {


    private UUID id;
    private String name;

    private String description;

    private String category;

    private Long version;

    private UUID shopId;
    private String shopName;

    private List<DtoTieredPrice> tieredPrices = new ArrayList<>();
}
