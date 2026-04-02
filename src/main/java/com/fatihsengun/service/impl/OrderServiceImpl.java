package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;
import com.fatihsengun.entity.Order;
import com.fatihsengun.entity.OrderItem;
import com.fatihsengun.entity.User;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.OrderItemRepository;
import com.fatihsengun.repository.OrderRepository;
import com.fatihsengun.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IGlobalMapper globalMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private IdentityService identityService;


    @Override
    @Transactional
    public DtoOrder createOrder(DtoOrderIU dtoOrderIU) {

        User currentUser = identityService.getCurrentUser();

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();







        return null;
    }
}
