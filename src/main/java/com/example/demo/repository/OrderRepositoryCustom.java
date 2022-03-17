package com.example.demo.repository;

import com.example.demo.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findCustomerAllOrders(String customerId);

    List<Order> listOrdersByDate(Date startDate, Date endDate);

    Order findOrderById(String orderId);
}
