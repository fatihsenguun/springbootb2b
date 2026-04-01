package com.fatihsengun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCategoryIU {
    private String name;

    private String description;

    // Optional:
    // If null, this is created as a top-level Main Category.
    // If provided, this is created as a Sub-Category under that parent.
    private UUID parentId;
}
