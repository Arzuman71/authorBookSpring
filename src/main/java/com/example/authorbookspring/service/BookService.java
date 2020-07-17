package com.example.authorbookspring.service;


import com.example.authorbookspring.model.Book;
import com.example.authorbookspring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);

    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book getOne(int id) {
        return bookRepository.getOne(id);
    }
}
