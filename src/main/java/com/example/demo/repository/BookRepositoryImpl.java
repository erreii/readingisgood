package com.example.demo.repository;

import com.example.demo.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@AllArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateBookStock(String bookId, int stockNumber) {
        Query query = new Query(Criteria.where("id").is(bookId));
        Update update = new Update();
        update.set("stockNumber", stockNumber);

        mongoTemplate.updateFirst(query, update, Book.class);
    }

    public Book findBookById(String bookId) {
       return mongoTemplate.findById(bookId, Book.class);
    }
}
