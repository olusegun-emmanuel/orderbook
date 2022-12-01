package com.rmb.orderbook.services;

import com.rmb.orderbook.beans.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import com.rmb.orderbook.repository.OrderBookRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class OrdersListAction {

    @Autowired
    private OrderBookRepository orderBookRepository;

    public LinkedList<Orders> getOrdersList(Long price, String side) {
        LinkedList<Orders> linkedListOrders = null;
        try {
            List<Orders> orderList = orderBookRepository.findOrdersByPriceAndSide(price, side);
            if (!orderList.isEmpty()) {
                linkedListOrders = new LinkedList<Orders>(orderList);
            } else {
                System.out.println("No record found for " + side + " orders");
            }
        } catch (Exception ex) {
            System.out.println("Action Exception occurred here:" + ex);
        }
        return linkedListOrders;
    }
}
