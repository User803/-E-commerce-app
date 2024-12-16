package com.droid.E_commerce.app.repository;

import com.droid.E_commerce.app.entity.Order;
import com.droid.E_commerce.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
