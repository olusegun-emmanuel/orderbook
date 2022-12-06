package com.rmb.orderbook;


import com.rmb.orderbook.beans.OrderRequestEntity;
import com.rmb.orderbook.beans.Orders;
import com.rmb.orderbook.beans.OrdersResponse;
import com.rmb.orderbook.controllers.OrderBookController;
import com.rmb.orderbook.services.OrderCreateAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderBookControllerTest {

    @Autowired
    OrderBookController orderBookController;
    OrdersResponse ordersResponse;

    @Test
    @Order(1)
    public void getBuyOrderBySideAndPriority_Success(){
        ordersResponse =  orderBookController.getOrders(100L, "Buy");
        List<Orders> ordersList = ordersResponse.getOrders();
        System.out.println(ordersResponse.toString());
        assertNotNull(ordersResponse.getOrders());
        assertEquals(1, ordersList.size());
    }

    @Test
    @Order(2)
    public void getSellOrderBySideAndPriority_Success(){
        ordersResponse =  orderBookController.getOrders(700L, "Sell");
        List<Orders> ordersList = ordersResponse.getOrders();
        System.out.println(ordersResponse.toString());
        assertNotNull(ordersResponse.getOrders());
        assertEquals(1, ordersList.size());
    }

    @Test
    @Order(3)
    public void addBuyOrderBySideAndPriority_Success() {
        OrderRequestEntity orders= new OrderRequestEntity();
        orders.setOrderId("");
        orders.setQuantity(1L);
        orders.setPrice(120L);
        orders.setSide("Buy");
        ordersResponse =  orderBookController.addOrders(orders);
        assertNotNull(ordersResponse);
    }

    @Test
    @Order(4)
    public void addSellOrderBySideAndPriority_Success() {
        OrderRequestEntity orders= new OrderRequestEntity();
        orders.setOrderId("");
        orders.setQuantity(1L);
        orders.setPrice(130L);
        orders.setSide("Sell");
        ordersResponse =  orderBookController.addOrders(orders);
        assertNotNull(ordersResponse);
    }

    @Test
    @Order(5)
    public void modifyBuyOrderBySideAndPriority_Success() {
        ordersResponse = orderBookController.updateOrder("A647336", 3L);
        List<Orders> ordersList = ordersResponse.getOrders();
        assertNotNull(ordersResponse.getOrders());
    }

  @Test
    @Order(6)
    public void modifySellOrderBySideAndPriority_Success() {
        ordersResponse = orderBookController.updateOrder("A647337", 3L);
        List<Orders> ordersList = ordersResponse.getOrders();
        System.out.println(ordersResponse.toString());
        assertNotNull(ordersResponse.getOrders());
    }


    @Test
    @Order(7)
    public void removeBuyOrderBySideAndPriority_Success() {
        ordersResponse = orderBookController.deleteOrder("A647335");
        List<Orders> ordersList = ordersResponse.getOrders();
        assertNotNull(ordersResponse.getOrders());
        assertEquals(6, ordersList.size());
    }

    @Test
    @Order(8)
    public void removeSellOrderBySideAndPriority_Success() {
        ordersResponse = orderBookController.deleteOrder("A647338");
        List<Orders> ordersList = ordersResponse.getOrders();
        assertNotNull(ordersResponse.getOrders());
        assertEquals(6, ordersList.size());
    }


}
