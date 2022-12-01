package com.rmb.orderbook.services;


import com.rmb.orderbook.beans.OrderRequestEntity;
import com.rmb.orderbook.beans.Orders;
import com.rmb.orderbook.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderUpdateAction {

    @Autowired
    private OrderBookRepository orderBookRepository;

    public LinkedList<Orders> modifyOrder(String orderId, Long quantity) {
        LinkedList<Orders> persistedOrdersList = null;
        try {
            List<Orders> ordersList = orderBookRepository.findAll();
            Orders order = orderBookRepository.findOrderByOrderId(orderId);
            order.setQuantity(quantity);

            //process order list
            persistedOrdersList = new LinkedList<>(ordersList);
            int orderIndex = getOrderIndex(ordersList, orderId);
            persistedOrdersList.remove(orderIndex);
            persistedOrdersList.add(order);

            //persist data
            orderBookRepository.delete(order);
            orderBookRepository.save(order);
        } catch (Exception ex) {
            System.out.println("Action Exception occurred here:" + ex);
        }
        return persistedOrdersList;
    }

    private int getOrderIndex(List<Orders> ordersLinkedList, String orderId) {
        int  counter = -1;
        for(Orders order : ordersLinkedList){
            counter++;
            if (order.getOrderId().equals(orderId)) {
                break;
            }
        }
        return counter;
    }

}
