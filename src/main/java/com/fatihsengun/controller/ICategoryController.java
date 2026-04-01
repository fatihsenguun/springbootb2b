package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoCategory;
import com.fatihsengun.dto.DtoCategoryIU;
import com.fatihsengun.entity.RootResponseEntity;

public interface ICategoryController {

    public RootResponseEntity<DtoCategory> create(DtoCategoryIU dtoCategoryIU);
}
