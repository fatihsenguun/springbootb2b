package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoCategory;
import com.fatihsengun.dto.DtoCategoryIU;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.Category;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
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
    public DtoCategory save(DtoCategoryIU dtoCategoryIU) {

        Category category = globalMapper.toCategoryEntity(dtoCategoryIU);


        if (dtoCategoryIU.getParentId() != null) {

            Category parentCategory = categoryRepository.findById(dtoCategoryIU.getParentId())
                    .orElseThrow(() -> new BaseException(new ErrorMessage(
                            MessageType.NO_RECORD_EXIST, "Catrgory not found")));

            category.setParentCategory(parentCategory);

        }
        Category savedCategory = categoryRepository.save(category);
        
        return globalMapper.toDtoCategory(savedCategory);
    }
}
