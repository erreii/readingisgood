package com.example.demo;

import com.example.demo.model.Book;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

public class BookControllerTests {
    public RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setTitle("The Lord Of The Rings");
        book.setAuthor("J.R.R Tolkien");

        URI location = restTemplate.postForLocation("http://localhost:8080/api/v1/book/saveBook", book);

        Book bookTest = restTemplate.getForObject(Objects.requireNonNull(location), Book.class);
        MatcherAssert.assertThat(Objects.requireNonNull(bookTest).getTitle(), Matchers.equalTo(book.getTitle()));
        MatcherAssert.assertThat(bookTest.getAuthor(), Matchers.equalTo(bookTest.getAuthor()));
    }
}
