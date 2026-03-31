package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoBusinessProfile;
import com.fatihsengun.dto.DtoBusinessProfileIU;
import com.fatihsengun.entity.RootResponseEntity;

public interface IBusinessProfileController {

    public RootResponseEntity<DtoBusinessProfile> create(DtoBusinessProfileIU dtoBusinessProfileIU);

}
