package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;
import com.fatihsengun.entity.RootResponseEntity;

public interface IOrderController {

    public RootResponseEntity<DtoOrder> createOrder(DtoOrderIU dtoOrderIU);
}
