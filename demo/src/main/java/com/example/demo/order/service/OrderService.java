package com.example.demo.order.service;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTO> list(long accountId);

    OrderDTO read(Long orderId);

    int delete(Long orderId);

}
