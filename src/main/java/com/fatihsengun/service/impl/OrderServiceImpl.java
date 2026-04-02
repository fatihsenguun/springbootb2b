package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;
import com.fatihsengun.dto.DtoOrderItemIU;
import com.fatihsengun.entity.Order;
import com.fatihsengun.entity.OrderItem;
import com.fatihsengun.entity.Product;
import com.fatihsengun.entity.User;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.OrderItemRepository;
import com.fatihsengun.repository.OrderRepository;
import com.fatihsengun.repository.ProductRepository;
import com.fatihsengun.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductRepository productRepository;


    @Override
    @Transactional
    public DtoOrder createOrder(DtoOrderIU dtoOrderIU) {

        User currentUser = identityService.getCurrentUser();


        List<UUID> productIds = dtoOrderIU.getItems().stream()
                .map(DtoOrderItemIU::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findByIdIn(productIds);

        Map<UUID, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));


        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();

        for (DtoOrderItemIU itemUI : dtoOrderIU.getItems()) {

            // 4. Get the product instantly from memory, zero database hits!
            Product product = productMap.get(itemUI.getProductId());

            if (product == null) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Product not found: " + itemUI.getProductId()));
            }

            if (product.getStock() < itemUI.getQuantity()) {
                throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Insufficient stock for: " + product.getName()));
            }

//            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(itemUI.getQuantity()));
//            total = total.add(lineTotal);
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(product);
//            orderItem.setQuantity(itemUI.getQuantity());
//            orderItem.setPriceAtPurchase(product.getPrice());
//            orderItem.setOrder(order);
        }



        return null;
    }
}
