package com.fatihsengun.mapper;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    AuthResponse toAuthResponseLogin(DtoLogin dtoLogin);

    BusinessProfile toBusinessProfileEntity(DtoBusinessProfileIU dtoBusinessProfileIU);

    @Mapping(target = "user", ignore = true)
    DtoBusinessProfile toDtoBusinessProfile(BusinessProfile businessProfile);


    DtoUser toDtoUser(User user);

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.companyName", target = "shopName")
    @Mapping(source = "category.id", target = "categoryId")       // Map category ID
    @Mapping(source = "category.name", target = "categoryName")
    DtoProduct toDtoProduct(Product product);

    @Mapping(target = "category", ignore = true)
    Product toProductEntity(DtoProductIU dtoProductIU);

    @Mapping(source = "parentCategory.id", target = "parentId")
    DtoCategory toDtoCategory(Category category);

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toCategoryEntity(DtoCategoryIU dtoCategoryIU);

    DtoOrder toDtoOrder(Order order);

    Order toOrderEntity(DtoOrderIU dtoOrderIU);

}
