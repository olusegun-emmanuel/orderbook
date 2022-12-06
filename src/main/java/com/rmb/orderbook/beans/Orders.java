package com.rmb.orderbook.beans;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "orderid")
    private String orderId;
    private Long price;
    private Long quantity;
    private String side;

    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Orders(){}

    public Orders(String orderId, Long price, Long quantity, String side, String status) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.status = status;
    }
}
