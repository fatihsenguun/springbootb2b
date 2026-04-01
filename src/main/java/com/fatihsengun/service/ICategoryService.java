package com.fatihsengun.service;

import com.fatihsengun.dto.DtoCategory;
import com.fatihsengun.dto.DtoCategoryIU;
import com.fatihsengun.dto.DtoProductIU;

public interface ICategoryService {

    public DtoCategory save(DtoCategoryIU dtoCategoryIU);
}
