package com.fatihsengun.service;

import com.fatihsengun.dto.DtoBusinessProfile;
import com.fatihsengun.dto.DtoBusinessProfileIU;

public interface IBusinessProfileService {

   public DtoBusinessProfile createBusinessProfile(DtoBusinessProfileIU dtoBusinessProfileIU);

}
