package com.rmb.orderbook.services;


import com.rmb.orderbook.beans.OrderRequestEntity;
import com.rmb.orderbook.beans.Orders;
import com.rmb.orderbook.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderCreateAction {

    @Autowired
    private OrderBookRepository orderBookRepository;
    private final String buyAppend = "A";
    private final String sellAppend = "B";

    public LinkedList<Orders> addNewOrder(OrderRequestEntity orderRequestEntity) {
        Orders orders = new Orders(this. generateUniqueOrderId(orderRequestEntity.getSide()), orderRequestEntity.getPrice(),
                orderRequestEntity.getQuantity(), orderRequestEntity.getSide());
        List<Orders> ordersList = orderBookRepository.findAll();
        Orders ord = ordersList.get(ordersList.size()-1);
        orders.setId(ord.getId() + 1);
        LinkedList<Orders> persistedOrdersList = new LinkedList<>(ordersList);
        if(!persistedOrdersList.isEmpty()) {
            persistedOrdersList.add(orders);
            orderBookRepository.save(orders);
        }

        return persistedOrdersList;
    }

    public String generateUniqueOrderId(String side) {
        int min = 100000;
        int max = 999999;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return side.equals("Buy") ? buyAppend + Integer.toString(random_int) : sellAppend + Integer.toString(random_int);
    }

}
