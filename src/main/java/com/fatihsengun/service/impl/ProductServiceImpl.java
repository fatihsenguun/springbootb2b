package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoProduct;
import com.fatihsengun.dto.DtoProductIU;
import com.fatihsengun.entity.BusinessProfile;
import com.fatihsengun.entity.Product;
import com.fatihsengun.entity.TieredPrice;
import com.fatihsengun.entity.User;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
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


    @Override
    @Transactional
    public DtoProduct save(DtoProductIU dtoProductIU) {


        User user = identityService.getCurrentUser();
        BusinessProfile businessProfile = user.getBusinessProfile();
        if (businessProfile == null) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "You must create a Business Profile before adding products."));
        }

        Product product = globalMapper.toProductEntity(dtoProductIU);
        product.setShop(businessProfile);

        if (product.getTieredPrices() != null) {
            for (TieredPrice price : product.getTieredPrices()) {
                price.setProduct(product);
            }
        }

        DtoProduct dtoProduct = globalMapper.toDtoProduct(productRepository.save(product));
        dtoProduct.setShopId(businessProfile.getId());
        dtoProduct.setShopName(businessProfile.getCompanyName());

        return dtoProduct ;
    }
}
