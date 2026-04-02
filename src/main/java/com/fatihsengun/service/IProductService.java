package com.fatihsengun.service;

import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProductService {

    public DtoProduct save(DtoProductIU dtoProductIU);

    public Page<DtoProduct> findByCategory(UUID categoryId, Pageable pageable);

    public Page<DtoProduct> findAll(Pageable pageable);


}
