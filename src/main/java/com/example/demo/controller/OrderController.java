package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.exception.CountExceedException;
import com.example.demo.model.Book;
import com.example.demo.model.Order;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderService;
import com.example.demo.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.example.demo.constants.Constants.INT_ZERO;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @PostMapping("/saveOrder")
    public ResponseEntity<Object> saveOrder(@RequestBody OrderDto orderDto) {
        Order orderRequest = modelMapper.map(orderDto, Order.class);
        try {
            validateOrder(orderRequest);
            Book book = bookService.findBookById(String.valueOf(orderRequest.getBookId()));
            int orderCount = orderRequest.getOrderCount();
            int stockCount = book.getStockNumber();
            if (orderCount <= stockCount) {
                int remaining = stockCount - orderCount;
                bookService.updateBookStock(book.getId(), remaining);
                calculateAmount(orderRequest, book.getPrice());
                orderService.saveOrder(orderRequest);
                return ResponseHandler.generateResponse("Successfully saved data!", HttpStatus.OK, orderRequest);
            }
            return ResponseHandler.generateResponse("Order exceed!", HttpStatus.MULTI_STATUS, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    private void calculateAmount(Order order, BigDecimal itemPrice) {
        int orderCount = order.getOrderCount();
        BigDecimal amount = itemPrice.multiply(new BigDecimal(orderCount));
        order.setAmount(amount);
    }

    @GetMapping("/findOrder/{id}")
    public ResponseEntity<Object> findOrder(@PathVariable("id") String id) {
        try {
            Order order = orderService.findOrderById(id);
            return ResponseHandler.generateResponse("findOrder", HttpStatus.OK, order);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/listOrder")
    public ResponseEntity<Object> listOrdersByDate(@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr) {
        try {
            Date startDate = Utils.convertStringToDate(startDateStr, Utils.DATE_FORMAT);
            Date endDate = Utils.convertStringToDate(endDateStr, Utils.DATE_FORMAT);
            List<Order> orderList = orderService.listOrdersByDate(startDate, endDate);
            return ResponseHandler.generateResponse("listOrdersByDate result", HttpStatus.OK, orderList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    private void validateOrder(Order order) {
        if (order.getOrderCount() <= INT_ZERO || order.getBookId() <= INT_ZERO) {
            throw new CountExceedException("Check count please!");
        }
    }


}
