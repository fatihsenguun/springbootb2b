package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IProductController;
import com.fatihsengun.controller.RestRootResponseController;
import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/all")
    public RootResponseEntity<Page<DtoProduct>> getAll(
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return ok(productService.findAll(pageable));
    }

    @GetMapping("/category/{categoryId}")
    public RootResponseEntity<Page<DtoProduct>> getByCategory(
            @PathVariable UUID categoryId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ok(productService.findByCategory(categoryId, pageable));
    }
}
