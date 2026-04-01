package com.fatihsengun.service;

import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;

public interface IProductService {

    public DtoProduct save(DtoProductIU dtoProductIU);
}
