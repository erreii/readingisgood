package com.example.demo.service;

import com.example.demo.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    Order findOrderById(String id);

    List<Order> findCustomerAllOrders(String customerId);

    List<Order> listOrdersByDate(Date startDate, Date endDate);
}
