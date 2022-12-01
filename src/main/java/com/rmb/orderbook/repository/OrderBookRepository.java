package com.rmb.orderbook.repository;

import com.rmb.orderbook.beans.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<Orders, Integer>{
    List<Orders> findOrdersByPriceAndSide(Long price, String side);
    Orders findOrderByOrderId(String orderId);
}
