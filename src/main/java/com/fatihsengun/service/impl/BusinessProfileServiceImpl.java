package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoBusinessProfile;
import com.fatihsengun.dto.DtoBusinessProfileIU;
import com.fatihsengun.entity.BusinessProfile;
import com.fatihsengun.entity.User;
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

        User user = identityService.getCurrentUser();
        BusinessProfile businessProfile = globalMapper.toBusinessProfileEntity(dtoBusinessProfileIU);

        businessProfile.setUser(user);
        businessProfile.setAverageRating(null);
        return globalMapper.toDtoBusinessProfile(businessProfileRepository.save(businessProfile));

    }
}
