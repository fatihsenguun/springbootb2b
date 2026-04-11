package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IOrderController;
import com.fatihsengun.controller.RestRootResponseController;
import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl extends RestRootResponseController implements IOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Override
    @PostMapping("/create")
    public RootResponseEntity<DtoOrder> createOrder(@Valid @RequestBody DtoOrderIU dtoOrderIU) {
        return ok(orderService.createOrder(dtoOrderIU));
    }



    @Override
    @GetMapping("/my-purchases")
    @PreAuthorize("hasAnyRole('BUYER', 'SELLER')")
    public RootResponseEntity<List<DtoOrder>> getMyPurchases() {
        return null;
    }



    @Override
    @GetMapping("/my-sales")
    @PreAuthorize("hasRole('SELLER')")
    public RootResponseEntity<List<DtoOrder>> getMySales() {
        return null;
    }
}
