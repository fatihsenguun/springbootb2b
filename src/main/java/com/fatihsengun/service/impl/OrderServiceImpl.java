package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoOrder;
import com.fatihsengun.dto.DtoOrderIU;
import com.fatihsengun.dto.DtoOrderItemIU;
import com.fatihsengun.entity.*;
import com.fatihsengun.enums.OrderStatus;
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

        // 1. Batch Fetch Products (The N+1 Fix)
        List<UUID> productIds = dtoOrderIU.getItems().stream()
                .map(DtoOrderItemIU::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findByIdIn(productIds);
        Map<UUID, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        BigDecimal masterTotal = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();

        BusinessProfile shopForThisOrder = null;

        // 2. Loop and Calculate
        for (DtoOrderItemIU itemUI : dtoOrderIU.getItems()) {

            Product product = productMap.get(itemUI.getProductId());

            if (product == null || product.isDeleted()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Product not found or unavailable."));
            }

            if (product.getStock() < itemUI.getQuantity()) {
                throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Insufficient stock for: " + product.getName()));
            }

            // 👉 THE B2B MAGIC: Get the volume price
            BigDecimal volumePrice = calculateB2BUnitPrice(product, itemUI.getQuantity());

            // Calculate the total for the master invoice (we don't save this to the item, just add it to the cart total)
            BigDecimal lineTotal = volumePrice.multiply(BigDecimal.valueOf(itemUI.getQuantity()));
            masterTotal = masterTotal.add(lineTotal);

            // Create the OrderItem mapping to YOUR exact entity fields
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemUI.getQuantity());
            orderItem.setPriceAtPurchase(volumePrice); // Saving the locked-in volume price here!
            orderItem.setOrder(order);

            orderItems.add(orderItem);

            // Ensure all items belong to the same shop
            if (shopForThisOrder == null) {
                shopForThisOrder = product.getShop();
            } else if (!shopForThisOrder.getId().equals(product.getShop().getId())) {
                throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "An order can only contain items from a single shop. Please split your cart."));
            }

            // Update product inventory & analytics
            product.setStock(product.getStock() - itemUI.getQuantity());
            product.setTotalSalesCount(product.getTotalSalesCount() + itemUI.getQuantity());
        }

        // 3. Finalize and Save the Order
        String generatedOrderNumber = "TRD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        order.setOrderNumber(generatedOrderNumber);
        order.setBuyer(currentUser);
        order.setShop(shopForThisOrder);
        order.setTotalAmount(masterTotal);
        order.setStatus(OrderStatus.PENDING);
        order.setItems(orderItems);

        DtoOrder dtoOrder = globalMapper.toDtoOrder(orderRepository.save(order));

        dtoOrder.setBuyerId(currentUser.getId());
        dtoOrder.setBuyerName(currentUser.getFullName());

        return dtoOrder;
    }

    @Override
    public List<DtoOrder> getMyPurchases() {
        User currentUser = identityService.getCurrentUser();
        List<Order> orders = orderRepository.findAllByBuyerIdOrderByCreatedAtDesc(currentUser.getId());

        return orders.stream().map(order -> {
            DtoOrder dtoOrder = globalMapper.toDtoOrder(order);

            dtoOrder.setBuyerId(currentUser.getId());
            dtoOrder.setBuyerName(currentUser.getFullName());
            return dtoOrder;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DtoOrder> getMySales() {
        User currentUser = identityService.getCurrentUser();
        BusinessProfile shop = currentUser.getBusinessProfile();

        if (shop == null) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "You do not have a registered shop profile."));
        }
        List<Order> orders = orderRepository.findAllByShopIdOrderByCreatedAtDesc(shop.getId());

        return orders.stream().map(order -> {
            DtoOrder dtoOrder = globalMapper.toDtoOrder(order);

            dtoOrder.setBuyerId(order.getBuyer().getId());
            dtoOrder.setBuyerName(order.getBuyer().getFullName());

            return dtoOrder;
        }).collect(Collectors.toList());
    }

    /**
     * Helper Method: Finds the correct unit price based on volume quantity.
     */
    private BigDecimal calculateB2BUnitPrice(Product product, Integer quantity) {

        if (product.getTieredPrices() == null || product.getTieredPrices().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "No pricing configured for product: " + product.getName()));
        }

        for (TieredPrice tier : product.getTieredPrices()) {
            boolean meetsMin = quantity >= tier.getMinQuantity();
            boolean meetsMax = tier.getMaxQuantity() == null || quantity <= tier.getMaxQuantity();

            if (meetsMin && meetsMax) {
                return tier.getUnitPrice();
            }
        }

        throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,
                "No matching price tier found for quantity " + quantity + " on product: " + product.getName()));
    }


}
