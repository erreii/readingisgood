package com.example.demo.service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public void saveBook(Book book) {
        log.info("BookService -- saveBook() -- started : " + book);
        bookRepository.insert(book);
        log.info("BookService -- saveBook() -- finished : " + book);
    }

    public void updateBookStock(String id, int stockCount) {
        log.info("BookService -- updateBookStock() -- started : BookId: " + id);
        bookRepository.updateBookStock(id, stockCount);
        log.info("BookService -- updateBookStock() -- finished : BookId: " + id);
    }

    public Book findBookById(String id) throws BookNotFoundException {
        Book book = bookRepository.findBookById(id);
        if (book == null) {
            throw new BookNotFoundException("Book not found with id : " + id);
        }
        return book;
    }
}
