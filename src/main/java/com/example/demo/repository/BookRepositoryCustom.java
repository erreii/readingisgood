package com.example.demo.repository;

import com.example.demo.model.Book;

public interface BookRepositoryCustom {
    void updateBookStock(String bookId, int stockNumber);

    Book findBookById(String id);
}
