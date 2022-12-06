package com.rmb.orderbook.services;


import com.rmb.orderbook.beans.OrderRequestEntity;
import com.rmb.orderbook.beans.Orders;
import com.rmb.orderbook.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderCreateAction {

    @Autowired
    private OrderBookRepository orderBookRepository;
    private final String buyAppend = "A";
    private final String sellAppend = "B";
    private final String sellSide = "Sell";
    private final String buySide = "Buy";
    private final String initialStatus = "New";
    private final String fullyExecutedStatus = "Executed";
    private final String partiallyExecutedStatus = "Partially Executed";

    public LinkedList<Orders> addNewOrder(OrderRequestEntity orderRequestEntity) {
        List<Orders> fillAbleList = null;
        List<Orders> filledList = new LinkedList<>();
        Orders orders = new Orders(this. generateUniqueOrderId(orderRequestEntity.getSide()), orderRequestEntity.getPrice(),
                orderRequestEntity.getQuantity(), orderRequestEntity.getSide(), initialStatus);
        List<Orders> ordersList = orderBookRepository.findAll();
        Orders ord = ordersList.get(ordersList.size()-1);
        orders.setId(ord.getId() + 1);
        LinkedList<Orders> persistedOrdersList = new LinkedList<>(ordersList);

        //check for sale orders
        if(!orders.getSide().equals(sellSide)) {
            persistedOrdersList.add(orders);
            orderBookRepository.save(orders);
            return persistedOrdersList;
        }
        else {
            fillAbleList =  this.getMatchingOrders( persistedOrdersList, orders.getPrice());
            if (fillAbleList.isEmpty()) {
                persistedOrdersList.add(orders);
                orderBookRepository.save(orders);
                return persistedOrdersList;
            } else {
                //implement matching
                Collections.reverse(fillAbleList);
                Long settQuantity = orders.getQuantity();
                for(Orders fillAbleOrder : fillAbleList) {
                    if(settQuantity > fillAbleOrder.getQuantity()){
                        fillAbleOrder.setStatus(fullyExecutedStatus);
                        filledList.add(fillAbleOrder);
                        settQuantity = settQuantity - fillAbleOrder.getQuantity();
                    } else {
                        fillAbleOrder.setStatus(partiallyExecutedStatus);
                        Long partialFill = fillAbleOrder.getQuantity() - settQuantity;;
                        fillAbleOrder.setQuantity(partialFill);
                        filledList.add(fillAbleOrder);
                        break;
                    }
                }

                persistedOrdersList = this.modifyOrderList(filledList);
                orders.setStatus(fullyExecutedStatus);
                persistedOrdersList.add(orders);
                orderBookRepository.save(orders);
                persistedOrdersList.addAll(filledList);
                orderBookRepository.saveAll(filledList);
            }
        }
        return persistedOrdersList;
    }

    private List<Orders> getMatchingOrders( LinkedList<Orders> persistedOrdersList, Long price){
        List<Orders> matchingOrders = new LinkedList<>();
        for (Orders value : persistedOrdersList) {
            if ((Objects.equals(value.getPrice(), price)) &
                    (value.getSide().equals(buySide)) & (value.getStatus().equals(initialStatus) |
                    (value.getStatus().equals(partiallyExecutedStatus)))) {
                matchingOrders.add(value);
            }
        }
        return matchingOrders;
    }


    private LinkedList<Orders> modifyOrderList(List<Orders> ordersList){
        List<Orders> allDbOrdersList = orderBookRepository.findAll();
        LinkedList<Orders> persistenceOrdersList = new LinkedList<>(allDbOrdersList);
        System.out.println(persistenceOrdersList.size());
        for(Orders OrderList : ordersList){
            int indexCounter = this.getOrderIndex(persistenceOrdersList, OrderList.getOrderId());
            persistenceOrdersList.remove(indexCounter);
            orderBookRepository.deleteById(OrderList.getId());
        }
        return persistenceOrdersList;
    }

    public String generateUniqueOrderId(String side) {
        int min = 100000;
        int max = 999999;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return side.equals("Buy") ? buyAppend + Integer.toString(random_int) : sellAppend + Integer.toString(random_int);
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
