package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.RootResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProductController {
    public RootResponseEntity<DtoProduct> save(DtoProductIU dtoProductIU);

    public RootResponseEntity<Page<DtoProduct>> getAll(Pageable pageable);
    public RootResponseEntity<Page<DtoProduct>> getByCategory(UUID categoryId, Pageable pageable);
}
