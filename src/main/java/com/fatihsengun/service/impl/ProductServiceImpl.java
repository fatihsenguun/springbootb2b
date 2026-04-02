package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.*;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.ICategoryRepository;
import com.fatihsengun.repository.ProductRepository;
import com.fatihsengun.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private IGlobalMapper globalMapper;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    @Transactional
    public DtoProduct save(DtoProductIU dtoProductIU) {

        Category category = categoryRepository.findById(dtoProductIU.getCategory())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Category not found.")));

        User user = identityService.getCurrentUser();
        BusinessProfile businessProfile = user.getBusinessProfile();
        if (businessProfile == null) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "You must create a Business Profile before adding products."));
        }

        Product product = globalMapper.toProductEntity(dtoProductIU);

        product.setShop(businessProfile);
        product.setCategory(category);
        if (product.getTieredPrices() != null) {
            for (TieredPrice price : product.getTieredPrices()) {
                price.setProduct(product);
            }
        }


        Product savedProduct = productRepository.save(product);
        return globalMapper.toDtoProduct(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DtoProduct> findByCategory(UUID categoryId, Pageable pageable) {

        Category rootCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Category not found.")));

        List<UUID> categoryIds = new ArrayList<>();

        collectCategoryIds(rootCategory, categoryIds);

        return productRepository.findByCategoryIdIn(categoryIds, pageable)
                .map(globalMapper::toDtoProduct);
    }

    private void collectCategoryIds(Category category, List<UUID> collectedIds) {
        collectedIds.add(category.getId());

        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
            for (Category subCategory : category.getSubCategories()) {
                collectCategoryIds(subCategory, collectedIds);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DtoProduct> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(globalMapper::toDtoProduct);
    }
}
