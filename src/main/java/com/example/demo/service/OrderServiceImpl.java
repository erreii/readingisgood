package com.example.demo.service;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public void saveOrder(Order order) {
        orderRepository.insert(order);
    }


    public Order findOrderById(String id) {
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            throw new OrderNotFoundException("Order not found with id : " + id);
        }
        return order;
    }


    public List<Order> findCustomerAllOrders(String customerId) {
        return orderRepository.findCustomerAllOrders(customerId);
    }


    public List<Order> listOrdersByDate(Date startDate, Date endDate) {
        return orderRepository.listOrdersByDate(startDate, endDate);
    }
}
