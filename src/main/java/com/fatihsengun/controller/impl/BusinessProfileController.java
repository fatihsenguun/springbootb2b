package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IBusinessProfileController;
import com.fatihsengun.dto.DtoBusinessProfile;
import com.fatihsengun.dto.DtoBusinessProfileIU;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.IBusinessProfileService;
import com.fatihsengun.service.impl.BusinessProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business_profile")
public class BusinessProfileController extends RootResponseEntity implements IBusinessProfileController {

    @Autowired
    private BusinessProfileServiceImpl businessProfileService;


    @Override
    public RootResponseEntity<DtoBusinessProfile> create(DtoBusinessProfileIU dtoBusinessProfileIU) {
        return ok(businessProfileService.createBusinessProfile(dtoBusinessProfileIU));
    }
}
