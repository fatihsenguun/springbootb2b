package com.fatihsengun.mapper;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.BusinessProfile;
import com.fatihsengun.entity.Product;
import com.fatihsengun.entity.User;
import jdk.jfr.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    AuthResponse toAuthResponseLogin(DtoLogin dtoLogin);

    BusinessProfile toBusinessProfileEntity(DtoBusinessProfileIU dtoBusinessProfileIU);

    @Mapping(target = "user", ignore = true)
    DtoBusinessProfile toDtoBusinessProfile(BusinessProfile businessProfile);


    DtoUser toDtoUser(User user);

    DtoProduct toDtoProduct(Product product);

    Product toProductEntity(DtoProductIU dtoProductIU);

    @Mapping(source = "parentCategory.id", target = "parentId")
    DtoCategory toDtoCategory(Category category);

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toCategoryEntity(DtoCategoryIU dtoCategoryIU);

}
