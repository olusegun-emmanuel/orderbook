package com.rmb.orderbook.beans;

import java.util.List;

public class OrdersResponse {
	private List<Orders> orders;

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrdersResponse{" +
				"orders=" + orders +
				'}';
	}
}
