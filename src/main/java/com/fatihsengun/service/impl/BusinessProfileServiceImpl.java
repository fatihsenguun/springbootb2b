package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoBusinessProfile;
import com.fatihsengun.dto.DtoBusinessProfileIU;
import com.fatihsengun.entity.BusinessProfile;
import com.fatihsengun.entity.User;
import com.fatihsengun.enums.Role;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.BusinessProfileRepository;
import com.fatihsengun.service.IBusinessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileServiceImpl implements IBusinessProfileService {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private IGlobalMapper globalMapper;


    @Override
    public DtoBusinessProfile createBusinessProfile(DtoBusinessProfileIU dtoBusinessProfileIU) {

        BusinessProfile businessProfile = globalMapper.toBusinessProfileEntity(dtoBusinessProfileIU);

        User user = identityService.getCurrentUser();
        user.setRole(Role.ROLE_SELLER);
        user.setBusinessProfile(businessProfile);

        businessProfile.setUser(user);
        businessProfile.setAverageRating(null);

        DtoBusinessProfile savedBusiness = globalMapper.toDtoBusinessProfile(businessProfileRepository.save(businessProfile));

        savedBusiness.setUser(globalMapper.toDtoUser(user));

        return savedBusiness;

    }
}
