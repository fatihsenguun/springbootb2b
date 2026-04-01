package com.fatihsengun.dto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCategory {

    private UUID id;

    private String name;

    private String description;

    // FLATTENED: We only need the ID to know who the parent is. No infinite loops!
    private UUID parentId;

    // We keep subcategories so the frontend can draw a dropdown menu or category tree
    private List<DtoCategory> subCategories = new ArrayList<>();
}
