package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookService bookService;

    @PostMapping("/saveBook")
    public ResponseEntity<Object> saveBook(@RequestBody BookDto bookDto) {
        Book bookRequest = modelMapper.map(bookDto, Book.class);
        try {
            bookService.saveBook(bookRequest);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, bookRequest);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/updateBookStock/{id}")
    public ResponseEntity<Object> updateBookStock(@PathVariable("id") String id, @RequestBody BookDto bookDto) {
        try {
            Book book = bookService.findBookById(id);
            if (book != null && book.getStockNumber() >= bookDto.getStockNumber()) {
                bookService.updateBookStock(id, bookDto.getStockNumber());
                return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, bookDto);
            }
            return ResponseHandler.generateResponse("Check stock count of bookId: " + id, HttpStatus.MULTI_STATUS, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
