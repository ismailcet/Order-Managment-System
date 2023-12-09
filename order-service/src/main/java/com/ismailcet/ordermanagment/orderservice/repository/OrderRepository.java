package com.ismailcet.ordermanagment.orderservice.repository;

import com.ismailcet.ordermanagment.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
