package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IProductController;
import com.fatihsengun.controller.RestRootResponseController;
import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl extends RestRootResponseController implements IProductController {

    @Autowired
    private ProductServiceImpl productService;


    @Override
    @PostMapping("/save")
    public RootResponseEntity<DtoProduct> save(@Valid @RequestBody DtoProductIU dtoProductIU) {

        return ok(productService.save(dtoProductIU));
    }
}
