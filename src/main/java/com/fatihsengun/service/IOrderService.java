package com.fatihsengun.service;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;

import java.util.List;

public interface IOrderService {

    public DtoOrder createOrder(DtoOrderIU dtoOrderIU);

   public List<DtoOrder> getMyPurchases();
    public List<DtoOrder> getMySales();

}
