package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;
import com.fatihsengun.entity.RootResponseEntity;

import java.util.List;

public interface IOrderController {

    public RootResponseEntity<DtoOrder> createOrder(DtoOrderIU dtoOrderIU);

    public RootResponseEntity<List<DtoOrder>> getMyPurchases();

    public RootResponseEntity<List<DtoOrder>> getMySales();
}
