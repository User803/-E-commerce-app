package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.Order;
import com.droid.E_commerce.app.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void createOrder(Order order);

    List<Order> findOrderByUser(User user);

    void updateOrder(Order order);
    void deleteOrder(Long id);
}
