package com.fatihsengun.service;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;

public interface IOrderService {

    public DtoOrder createOrder(DtoOrderIU dtoOrderIU);

}
