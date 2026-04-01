package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.RootResponseEntity;

public interface IProductController {
    public RootResponseEntity<DtoProduct> save(DtoProductIU dtoProductIU);
}
