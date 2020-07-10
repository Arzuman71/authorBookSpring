package com.example.authorbookspring.repository;


import com.example.authorbookspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {




}
