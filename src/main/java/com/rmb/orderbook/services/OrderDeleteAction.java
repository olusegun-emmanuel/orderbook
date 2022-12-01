package com.rmb.orderbook.services;


import com.rmb.orderbook.beans.OrderDeleteEntity;
import com.rmb.orderbook.beans.OrderRequestEntity;
import com.rmb.orderbook.beans.Orders;
import com.rmb.orderbook.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderDeleteAction {

    @Autowired
    private OrderBookRepository orderBookRepository;

    public LinkedList<Orders> removeOrder(String orderDelete) {
        List<Orders> ordersList = orderBookRepository.findAll();
        LinkedList<Orders> persistedOrdersList = new LinkedList<>(ordersList);
        int orderIndex = getOrderIndex(persistedOrdersList, orderDelete);
        persistedOrdersList.remove(orderIndex);
        this.deleteCustomerInfo(orderDelete);
        return persistedOrdersList;
    }

    private int getOrderIndex(LinkedList<Orders> ordersLinkedList, String orderId) {
        int  counter = -1;
        for(Orders order : ordersLinkedList){
            counter++;
            if (order.getOrderId().equals(orderId)) {
                break;
            }
        }
        return counter;
    }

    protected void deleteCustomerInfo(String orderId){
        Orders orders = orderBookRepository.findOrderByOrderId(orderId);
        orderBookRepository.delete(orders);
    }

}
