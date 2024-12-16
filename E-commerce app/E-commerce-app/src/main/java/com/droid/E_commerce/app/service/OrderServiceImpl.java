package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.Order;
import com.droid.E_commerce.app.entity.User;
import com.droid.E_commerce.app.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Order with id %s not found", id))
                );
    }

    @Transactional
    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void updateOrder(Order order) {
        orderRepository.findById(order.getId())
                .orElseThrow(
                        () -> new RuntimeException("Order with id %s not found".formatted(order.getId()))
                );

        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void deleteOrder(Long id) {
        orderRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User with id %s not found".formatted(id))
                );

        orderRepository.deleteById(id);
    }
}
