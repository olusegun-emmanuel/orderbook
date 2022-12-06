package com.rmb.orderbook.controllers;

import com.rmb.orderbook.beans.*;
import com.rmb.orderbook.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-book")
public class OrderBookController {

    @Autowired
    private OrdersListAction ordersListAction;
    @Autowired
    private OrderCreateAction orderCreateAction;
    @Autowired
    private OrderDeleteAction orderDeleteAction;

    @Autowired
    private OrderUpdateAction orderUpdateAction;


    @RequestMapping("/orders/{price}/{side}")
    //This api retrieves orders in FIFO based on id and side parameters (Requirement 1)
    public OrdersResponse getOrders(@PathVariable("price") Long price, @PathVariable("side") String side) {
        OrdersResponse ordersResponse = new OrdersResponse();
        List<Orders> ordersList = ordersListAction.getOrdersList(price, side);
        ordersResponse.setOrders(ordersList);
        return ordersResponse;
    }

    @PostMapping("/orders/create-order")
    //This api adds new orders to the orders list and follows FIFO priority (Requirement 2)
    public OrdersResponse addOrders(@RequestBody OrderRequestEntity orderRequestEntity) {
        OrdersResponse ordersResponse = new OrdersResponse();
        List<Orders> ordersList = orderCreateAction.addNewOrder(orderRequestEntity);
        ordersResponse.setOrders(ordersList);
        return ordersResponse;
    }


    @DeleteMapping("/orders/{orderId}")
    //This method deletes the order with specified orderId. (Requrement 3)
    public OrdersResponse deleteOrder(@PathVariable("orderId") String orderId){
        OrdersResponse ordersResponse = new OrdersResponse();
        List<Orders> ordersList = orderDeleteAction.removeOrder(orderId);
        ordersResponse.setOrders(ordersList);
        return ordersResponse;
    }


    @PutMapping("/orders/{orderId}/{quantity}")
    //This method deletes the order with specified orderId. (Requrement 3)
    public OrdersResponse updateOrder(@PathVariable("orderId") String orderId, @PathVariable("quantity") Long quantity){
        OrdersResponse ordersResponse = new OrdersResponse();
        List<Orders> ordersList = orderUpdateAction.modifyOrder(orderId, quantity);
        ordersResponse.setOrders(ordersList);
        return ordersResponse;
    }

}
