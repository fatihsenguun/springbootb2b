package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.ICategoryController;
import com.fatihsengun.controller.RestRootResponseController;
import com.fatihsengun.dto.DtoCategory;
import com.fatihsengun.dto.DtoCategoryIU;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryControllerImpl extends RestRootResponseController implements ICategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    @PostMapping("/create")
    public RootResponseEntity<DtoCategory> create(@Valid @RequestBody DtoCategoryIU dtoCategoryIU) {
        return ok(categoryService.save(dtoCategoryIU));
    }
}
