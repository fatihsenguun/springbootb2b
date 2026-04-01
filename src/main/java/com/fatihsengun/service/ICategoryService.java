package com.fatihsengun.service;

import com.fatihsengun.dto.DtoCategory;
import com.fatihsengun.dto.DtoProductIU;

public interface ICategoryService {

    public DtoCategory save(DtoProductIU dtoProductIU);
}
