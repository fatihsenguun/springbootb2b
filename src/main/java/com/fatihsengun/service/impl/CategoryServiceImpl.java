package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoCategory;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.ICategoryRepository;
import com.fatihsengun.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Override
    public DtoCategory save(DtoProductIU dtoProductIU) {


        return null;
    }
}
