package com.example.demo.service;

import com.example.demo.model.Book;

public interface BookService {

    void saveBook(Book book);

    void updateBookStock(String id, int stockCount);

    Book findBookById(String id);
}
