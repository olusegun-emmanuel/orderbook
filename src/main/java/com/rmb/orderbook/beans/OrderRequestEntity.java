package com.rmb.orderbook.beans;

import lombok.Getter;
import lombok.Setter;

public class OrderRequestEntity {

    @Getter
    @Setter
    private String orderId;
    @Getter
    @Setter
    private Long price;
    @Getter
    @Setter
    private Long quantity;
    @Getter
    @Setter
    private String side;
}
