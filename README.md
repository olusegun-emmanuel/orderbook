# orderbook

This is implemented to demonstrate the use of API in the design of orderbook.
Persistence to H2 in memory database is implemented to mimic real life scenarios as much as possible
(e.g. Asuuming trading closes 12 midnight dailt, data is persisted to have histoty of last trade data for the day)

LinnkedList was used to manage the storage of because of performace and the flexibility of data manipulations.

# orderbook API Samples

1.  http://localhost:8082/order-book/orders/100/Buy (GET)

2.  http://localhost:8082/order-book/orders/create-order (POST)
    {
      "orderId": "",
      "price": 300,
      "quantity": 5,
      "side": "Buy"
    }

3.  http://localhost:8082/order-book/orders/A647343 (DELETE)

4.  http://localhost:8082/order-book/orders/A647343/4 (PUT)
