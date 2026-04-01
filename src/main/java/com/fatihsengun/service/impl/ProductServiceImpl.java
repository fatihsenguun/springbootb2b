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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // 1. Fetch the category from the database
        Category category = categoryRepository.findById(dtoProductIU.getCategory())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Category not found.")));

        // 2. Fetch the current user's shop
        User user = identityService.getCurrentUser();
        BusinessProfile businessProfile = user.getBusinessProfile();
        if (businessProfile == null) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "You must create a Business Profile before adding products."));
        }

        // 3. Map the basic text fields
        Product product = globalMapper.toProductEntity(dtoProductIU);

        // 4. ATTACH THE ENTITIES BEFORE SAVING
        product.setShop(businessProfile);
        product.setCategory(category); // <-- Attach the category here!

        // 5. Link the prices (Bidirectional sync)
        if (product.getTieredPrices() != null) {
            for (TieredPrice price : product.getTieredPrices()) {
                price.setProduct(product);
            }
        }

        // 6. Save the product to the database
        Product savedProduct = productRepository.save(product);

        // 7. Map it back to the DTO.
        // Thanks to your IGlobalMapper, this single line automatically flattens
        // the shop and category IDs/Names!
        return globalMapper.toDtoProduct(savedProduct);
    }
}
