package com.example.project_class3_5.service.Impl;

import com.example.project_class3_5.dto.Request.OrderItemRequest;
import com.example.project_class3_5.dto.Request.OrderRequest;
import com.example.project_class3_5.dto.Response.OrderResponse;
import com.example.project_class3_5.entities.Order;
import com.example.project_class3_5.entities.OrderItem;
import com.example.project_class3_5.entities.Product;
import com.example.project_class3_5.entities.User;
import com.example.project_class3_5.exception.BadRequestException;
import com.example.project_class3_5.exception.ResourceNotFoundException;
import com.example.project_class3_5.mapper.OrderMapper;
import com.example.project_class3_5.repo.OrderItemRepository;
import com.example.project_class3_5.repo.OrderRepository;
import com.example.project_class3_5.repo.ProductRepository;
import com.example.project_class3_5.repo.UserRepository;
import com.example.project_class3_5.service.OrderService;
import com.example.project_class3_5.service.UserService;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Server
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with id: "+request.getUserId())
                );

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalAmount(BigDecimal.ZERO);

        List<OrderItemRequest> orderItemRequestList = request.getOrderItems();

        BigDecimal totalAmount = BigDecimal.ZERO;

        OrderItem orderItem = new OrderItem();

        List<OrderItem>  orderItemList = new ArrayList<>();

        for(OrderItemRequest orderItemRequest : orderItemRequestList){
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Product not found with id: "+orderItemRequest.getProductId())
                    );

            if((product.getStock() - orderItemRequest.getQuantity()) < 0){
                throw new BadRequestException("Product stock less than qty.");
            }

            int newStock = product.getStock() - orderItemRequest.getQuantity();
            product.setStock(newStock);
            productRepository.save(product);

            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            // save or orderItem to db
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);

            orderItemList.add(savedOrderItem);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())));

        }
        order.setOrderItems(orderItemList);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return List.of();
    }

    @Override
    public OrderResponse updateOrderStatus(Long id, String status) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
