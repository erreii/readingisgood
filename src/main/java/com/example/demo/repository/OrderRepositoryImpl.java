package com.example.demo.repository;

import com.example.demo.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> findCustomerAllOrders(String customerId) {
        Query query = new Query(Criteria.where("customerId").is(Long.valueOf(customerId)));
        return mongoTemplate.find(query, Order.class);
    }

    public List<Order> listOrdersByDate(Date startDate, Date endDate) {
        Query query = new Query(Criteria.where("orderDate").gte(startDate).lte(endDate));
        return mongoTemplate.find(query, Order.class);
    }

    public Order findOrderById(String orderId) {
       return mongoTemplate.findById(orderId, Order.class);
    }
}
